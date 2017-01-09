definition(
name: "Momentary Toggle",
namespace: "smartthings",
author: "SmartThings",
description: "Toggles light state",
category: "Convenience",
iconUrl: "https://s3.amazonaws.com/smartapp-icons/MyApps/Cat-MyApps.png",
iconX2Url: "https://s3.amazonaws.com/smartapp-icons/MyApps/Cat-MyApps@2x.png",
iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")

preferences {
section("Real Switch...") { 
input "realswitch", "capability.switch", 
title: "Real Switch...", 
required: true
}
section("Virtual Stand-in...") {
input "standin", "capability.switch",
title: "Stand In Virtual Switch...",
required: true
}
}

def installed() {
subscribe(standin, "switch.on", switchOnHandler)
subscribe(standin, "switch.off", switchOffHandler)
}

def updated() {
unsubscribe()
subscribe(standin, "switch.on", switchOnHandler)
subscribe(standin, "switch.off", switchOffHandler)
}

def switchOnHandler(evt) {
state.wasOn = realswitch.currentValue("switch") == "on"
state.wasOff = realswitch.currentValue("switch") == "off"
if(state.wasOff)realswitch.on()
if(state.wasOn)realswitch.off()

}

def switchOffHandler(evt) {
}