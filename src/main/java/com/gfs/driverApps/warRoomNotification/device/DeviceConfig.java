package com.gfs.driverApps.warRoomNotification.device;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gfs.driverApps.warRoomNotification.util.RestUtil;

@Configuration
public class DeviceConfig {

    @Bean(name = "deviceStateChange")
    public RestUtil<String> deviceStateChange(@Value("${device.token}") final String token,
            @Value("${device.state.url}") final String deviceStateUrl) {

        final RestUtil<String> deviceQuery = new RestUtil<>();
        deviceQuery.setAuthCreds(RestUtil.BEARER_AUTH_MODE, token);
        deviceQuery.setUrl(deviceStateUrl);
        deviceQuery.setHeader("content-type", APPLICATION_JSON_VALUE);
        return deviceQuery;
    }

    @Bean(name = "deviceBlink")
    public RestUtil<String> deviceBlink(@Value("${device.token}") final String token,
            @Value("${device.animation.url}") final String deviceAnimateUrl) {

        final RestUtil<String> deviceQuery = new RestUtil<>();
        deviceQuery.setAuthCreds(RestUtil.BEARER_AUTH_MODE, token);
        deviceQuery.setUrl(deviceAnimateUrl);
        deviceQuery.setHeader("content-type", APPLICATION_JSON_VALUE);
        return deviceQuery;
    }

}
