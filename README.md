---
title: Xplane
description: Cordova plugin to control X-Plane.
---
<!--
# license: Licensed to the Apache Software Foundation (ASF) under one
#         or more contributor license agreements.  See the NOTICE file
#         distributed with this work for additional information
#         regarding copyright ownership.  The ASF licenses this file
#         to you under the Apache License, Version 2.0 (the
#         "License"); you may not use this file except in compliance
#         with the License.  You may obtain a copy of the License at
#
#           http://www.apache.org/licenses/LICENSE-2.0
#
#         Unless required by applicable law or agreed to in writing,
#         software distributed under the License is distributed on an
#         "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#         KIND, either express or implied.  See the License for the
#         specific language governing permissions and limitations
#         under the License.
-->

# cordova-plugin-xplane 0.1.0 (alpha)

This plugin defines a global `Xplane` object, which is used to communicate with a running X-Plane instance.

## Installation

cordova plugin add https://github.com/angelsware/cordova-plugin-xplane

### Supported Platforms

- Android

### Quick Example

```js
Xplane.connect("localhost", 49009, 0, 100, function(result) {
	window.alert(JSON.stringify(result));
});

Xplane.sendTEXT("It works! :)", function(result) {
	window.alert(JSON.stringify(result));
});
```

### Reference

The Xplane Cordova plugin is an abstraction layer and uses Nasa's XPlaneConnect.

- https://github.com/nasa/XPlaneConnect/wiki/XPC-Client-Reference

```js
Xplane.connect(string:xpHOST, int:xpPort, int:port, int:timeout, function:callback);
Xplane.getCTRL(int:ac, function:callback);
Xplane.getDREF(string:dref, function:callback);
Xplane.getDREFs(string[]:drefs, function:callback);
Xplane.getPOSI(int:ac, function:callback);
Xplane.sendDREF(string:dref, float[]:values, function:callback);
Xplane.sendPOSI(float[]:values, int:ac, function:callback);
Xplane.sendTEXT(string:msg, function:callback);
```
