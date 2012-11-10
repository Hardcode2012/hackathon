/// <reference path="WCGapi.js" />
/// <reference path="jquery-1.5.1.js" />

var service;

$(function () {
    $("#initBtn").click(initWebRTC);
});

var initWebRTC = function () {
    service = new MediaServices("http://10.97.33.50:38080/HaikuServlet/rest/v2",
                        "sip:16509992355@vims1.com", "EECpa$$w0rd", "audio,video");
    service.turnConfig = "STUN 10.97.33.50:4242";

    service.onready = readyHandler;
    service.onclose = closeHandler;
    service.oninvite = inviteHandler;
    service.onstatechange = stateChangedHandler;
}


var closeHandler = function (evt) {
    prompt("Closed");
}

var errorHandler = function () {
    prompt("Error Handler");
}

var inviteHandler = function () {
    $("#dialog-confirm").dialog({
        resizable: false,
        height: 200,
        modal: true,
        buttons: {
            "Yes": function () {
                $(this).dialog("close");
            },
            "No": function () {
                $(this).dialog("close");
            }
        }
    });
}

var readyHandler = function () {
    prompt("Connected");
}

var stateChangedHandler = function (evt) {
    prompt("State Changed");
}