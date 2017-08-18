package com.angelsware.xplane;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import gov.nasa.xpc.XPlaneConnect;

public class Xplane extends CordovaPlugin {
	public Xplane() {
	}

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
	}

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if ("connect".equals(action)) {
			connect(args, callbackContext);
		} else if ("getDREF".equals(action)) {
			getDREF(args, callbackContext);
		} else if ("sendDREF".equals(action)) {
			sendDREF(args, callbackContext);
		} else if ("sendPOSI".equals(action)) {
			sendPOSI(args, callbackContext);
		} else if ("getPOSI".equals(action)) {
			getPOSI(args, callbackContext);
		} else if ("sendTEXT".equals(action)) {
			sendTEXT(args, callbackContext);
		} else if ("getCTRL".equals(action)) {
			getCTRL(args, callbackContext);
		} else {
			return false;
		}
		return true;
	}

	private void connect(JSONArray args, CallbackContext callbackContext) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("method", "connect");
		try {
			mXpc = new XPlaneConnect(args.getString(0), args.getInt(1), args.getInt(2));
			json.put("result","ok");
		} catch (Exception ex) {
			json.put("result","error");
			json.put("message", ex.getMessage());
		}
		callbackContext.success(json);
	}

	private void getDREF(JSONArray args, CallbackContext callbackContext) throws JSONException {
		String dref = args.getString(0);
		JSONObject r = null;
		try {
			r = successDREFResult(dref, "getDREF", mXpc.getDREF(dref));
		} catch (Exception ex) {
			r = failedDREFResult(dref, "getDREF", ex.getMessage());
		}
		callbackContext.success(r);
	}

	private void sendDREF(JSONArray args, CallbackContext callbackContext) throws JSONException {
		String dref = args.getString(0);
		JSONObject r = new JSONObject();
		try {
			float[] values = new float[args.getJSONArray(1).length()];
			for (int i = 0; i < args.getJSONArray(1).length(); ++i) {
				values[i] = (float)args.getJSONArray(1).getDouble(i);
			}
			mXpc.sendDREF(dref, values);
			r = successDREFResult(dref, "sendDREF", null);
		} catch (Exception ex) {
			r = failedDREFResult(dref, "sendDREF", ex.getMessage());
		}
		callbackContext.success(r);
	}

	private void sendPOSI(JSONArray args, CallbackContext callbackContext) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("method", "sendPOSI");
		float[] values = new float[args.getJSONArray(0).length()];
		for (int i = 0; i < args.getJSONArray(0).length(); ++i) {
			values[i] = (float)args.getJSONArray(0).getDouble(i);
		}
		try {
			mXpc.sendPOSI(values, args.getInt(1));
			json.put("result", "ok");
		} catch (Exception ex) {
			json.put("result", "error");
			json.put("message", ex.getMessage());
		}
		callbackContext.success(json);
	}

	private void getPOSI(JSONArray args, CallbackContext callbackContext) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("method", "getPOSI");
		try {
			float[] values = mXpc.getPOSI(args.getInt(0));
			json.put("result", "ok");
			JSONArray a = new JSONArray();
			for (int i = 0; i < values.length; ++i) {
				a.put(values[i]);
			}
			json.put("values", a);
		} catch (Exception ex) {
			json.put("result", "error");
			json.put("message", ex.getMessage());
		}
		callbackContext.success(json);
	}

	private void sendTEXT(JSONArray args, CallbackContext callbackContext) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("method", "sendTEXT");
		try {
			mXpc.sendTEXT(args.getString(0));
			json.put("result", "ok");
		} catch (Exception ex) {
			json.put("result", "error");
			json.put("message", ex.getMessage());
		}
		callbackContext.success(json);
	}

	private void getCTRL(JSONArray args, CallbackContext callbackContext) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("method", "getCTRL");
		try {
			float[] values = mXpc.getCTRL(args.getInt(0));
			json.put("result", "ok");
			JSONArray a = new JSONArray();
			for (int i = 0; i < values.length; ++i) {
				a.put(values[i]);
			}
			json.put("values", a);
		} catch (Exception ex) {
			json.put("result", "error");
			json.put("message", ex.getMessage());
		}
		callbackContext.success(json);
	}

	private JSONObject successDREFResult(String dref, String method, float[] values) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("result", "ok");
		json.put("method", method);
		json.put("dref", dref);
		if (values != null) {
			JSONArray a = new JSONArray();
			for (int i = 0; i < values.length; ++i) {
				a.put(values[i]);
			}
			json.put("values", a);
		}
		return json;
	}

	private JSONObject failedDREFResult(String dref, String method, String message) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("result", "error");
		json.put("method", method);
		json.put("dref", dref);
		json.put("message", message);
		return json;
	}

	private XPlaneConnect mXpc = null;
}
