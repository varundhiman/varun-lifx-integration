package com.gfs.driverApps.warRoomNotification;

public enum Status {

    OK("green"), SONAR_FAILURE("orange"), UNREACHABLE("");

    String color;

    Status(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

}
