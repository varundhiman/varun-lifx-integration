package com.gfs.driverApps.warRoomNotification.device;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfs.driverApps.warRoomNotification.Status;

public class DeviceCommand {

    public DeviceCommand() {
    }

    public DeviceCommand(Status status) {
        color = status.getColor();
    }

    String color;
    int duration = 3;
    int cycles = 5;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    @Override
    public String toString() {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (final JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
