package com.gfs.driverApps.warRoomNotification.sonar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.gfs.driverApps.warRoomNotification.sonar.model.SonarProjectStatus;
import com.gfs.driverApps.warRoomNotification.util.RestUtil;

@Configuration
public class SonarConfig {

    @Autowired
    MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Bean(name = "sonarQuery")
    public RestUtil<SonarProjectStatus> sonarQuery(@Value("${sonar.user}") final String username,
            @Value("${sonar.password}") final String password, @Value("${sonar.url}") final String sonarQueryUrl) {

        final RestUtil<SonarProjectStatus> sonarQuery = new RestUtil<>();
        sonarQuery.getRestTemplate().getMessageConverters().add(0, jacksonMessageConverter);
        sonarQuery.setAuthCreds(null, username, password);
        sonarQuery.setUrl(sonarQueryUrl);
        sonarQuery.setTypeOfT(SonarProjectStatus.class);
        return sonarQuery;
    }

}
