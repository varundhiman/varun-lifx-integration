package com.gfs.driverApps.warRoomNotification.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gfs.driverApps.warRoomNotification.util.RestUtil;

@Component
public class DeviceProxy {

    @Autowired
    RestUtil<String> deviceStateChange;

    @Autowired
    RestUtil<String> deviceBlink;

    public void changeDeviceState(DeviceCommand command) {
        deviceBlink.setBody(command.toString());
        deviceBlink.submitRequest();
        deviceStateChange.setBody(command.toString());
        deviceStateChange.putRequest();
    }

}
