package com.gfs.driverApps.warRoomNotification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gfs.util.spring.config.EncryptedPlaceholderConfigurer;

@Configuration
public class CommonConfig {

    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
        converter.setObjectMapper(mapper);

        return converter;
    }

    @Bean
    public EncryptedPlaceholderConfigurer encryptedPlaceholderConfigurer() {
        final EncryptedPlaceholderConfigurer config = new EncryptedPlaceholderConfigurer();
        config.setKey("edpDeo+/ZHY=");
        config.setLocation(new ClassPathResource("application.properties"));
        return config;
    }

}
