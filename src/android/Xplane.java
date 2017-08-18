package com.angelsware.xplane;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import gov.nasa.xpc.XPlaneConnect;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Xplane extends CordovaPlugin {
	public static final String TAG = "X-Plane";
	private XPlaneConnect mXpc = null;

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
		} else {
			return false;
		}
		return true;
	}

	private void connect(JSONArray args, CallbackContext callbackContext) {
		try {
			JSONObject json = new JSONObject();
			try {
				mXpc = new XPlaneConnect(args.getString(0), args.getInt(1), args.getInt(2));
				json.put("result","ok");
				json.put("method", "connect");
			} catch (Exception ex) {
				json.put("result","error");
				json.put("method", "connect");
				json.put("message", ex.getMessage());
			}
			callbackContext.success(json);
		} catch (Exception ex) {
		}
	}

	private void getDREF(JSONArray args, CallbackContext callbackContext) {
		try {
			String dref = args.getString(0);
			JSONObject r = null;
			try {
				r = successDREFResult(dref, "getDREF", mXpc.getDREF(dref));
			} catch (Exception ex) {
				r = failedDREFResult(dref, "getDREF", ex.getMessage());
			}
			callbackContext.success(r);
		} catch (Exception ex) {
		}
	}

	private void sendDREF(JSONArray args, CallbackContext callbackContext) {
		try {
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
		} catch (Exception ex) {
		}
	}

	private JSONObject successDREFResult(String dref, String method, float[] values) {
		JSONObject json = new JSONObject();
		try {
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
		} catch (Exception ex) {
		}
		return json;
	}

	private JSONObject failedDREFResult(String dref, String method, String message) {
		JSONObject json = new JSONObject();
		try {
			json.put("result", "error");
			json.put("method", method);
			json.put("dref", dref);
			json.put("message", message);
		} catch (Exception ex) {
		}
		return json;
	}

	/*
		{"result":"ok","method":"getDREF","dref":"sim/...","values":[1.0,2.0]}
		{"result":"ok","method":"sendDREF","dref":"sim/..."}
		{"result":"error","method":"sendDREF","message":"Could not..."}
	*/
}
