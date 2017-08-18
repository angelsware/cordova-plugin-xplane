package com.angelsware.xplane;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Xplane extends CordovaPlugin {
	public static final String TAG = "X-Plane";

	public Xplane() {
	}

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
	}

	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if ("connect".equals(action)) {
			JSONObject r = new JSONObject();
			r.put("connect", "ok");
			r.put("host", args.getString(0));
			r.put("xpPort", args.getLong(1));
			r.put("port", args.getLong(2));
			callbackContext.success(r);
		} else if ("getDREF".equals(action)) {
			JSONObject r = new JSONObject();
			r.put("message", "yay");
			callbackContext.success(r);
		} else {
			return false;
		}
		return true;
	}
}
