package com.burcu.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProjectConfig {


    @Bean
    public Cloudinary getCloudinary(){
        Map config= ObjectUtils.asMap(
                "cloud_name", "ds9csq0ac",
                "api_key", "181852922728463",
                "api_secret", "BQ6Djb3GC1X2Q-jo4ieslCdpKkY",
                 "secure",true);
        return new Cloudinary(config); // içerisi Map(config) alıyor
    }
}
