var exec = require('cordova/exec');

function Xplane() {
}

Xplane.prototype.connect = function(xpHost, xpPort, port, timeout, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'connect', [xpHost, xpPort, port, timeout]);
}

Xplane.prototype.close = function(successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'close', []);
}

Xplane.prototype.getDREF = function(dref, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'getDREF', [dref]);
}

Xplane.prototype.getDREFs = function(drefs, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'getDREFs', [drefs]);
}

Xplane.prototype.sendDREF = function(dref, values, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'sendDREF', [dref, values]);
}

Xplane.prototype.sendPOSI = function(values, ac, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'sendPOSI', [values, ac]);
}

Xplane.prototype.getPOSI = function(ac, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'getPOSI', [ac]);
}

Xplane.prototype.sendTEXT = function(msg, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'sendTEXT', [msg]);
}

Xplane.prototype.getCTRL = function(ac, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'getCTRL', [ac]);
}

Xplane.prototype.sendCTRL = function(ac, values, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'sendCTRL', [values, ac]);
}

Xplane.prototype.sendCOMM = function(comm, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'sendCOMM', [comm]);
}

module.exports = new Xplane();
