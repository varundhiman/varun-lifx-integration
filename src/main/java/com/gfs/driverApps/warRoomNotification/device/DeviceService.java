package com.gfs.driverApps.warRoomNotification.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gfs.driverApps.warRoomNotification.Status;

@Component
public class DeviceService {

    @Autowired
    DeviceProxy deviceProxy;

    public void changeDeviceState(Status status) {
        deviceProxy.changeDeviceState(new DeviceCommand(status));
    }

}
