var exec = require('cordova/exec');

function Xplane() {
}

Xplane.prototype.connect = function(xpHost, xpPort, port, successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'connect', [xpHost, xpPort, port]);
}

Xplane.prototype.getDREF = function(successCallback, errorCallback) {
	exec(successCallback, errorCallback, 'Xplane', 'getDREF', []);
}

module.exports = new Xplane();
