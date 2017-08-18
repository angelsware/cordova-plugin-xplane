var exec = require('cordova/exec');

function Xplane() {
}

Xplane.prototype.connect = function(xpHost, xpPort, port, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'connect', [xpHost, xpPort, port]);
}

Xplane.prototype.getDREF = function(dref, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'getDREF', [dref]);
}

Xplane.prototype.sendDREF = function(dref, values, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'sendDREF', [dref, values]);
}

Xplane.prototype.sendPOSI = function(values, ac, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'sendPOSI', [values, ac]);
}

module.exports = new Xplane();
