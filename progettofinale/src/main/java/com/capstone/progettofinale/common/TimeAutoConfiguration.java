package com.capstone.progettofinale.common;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore({ JacksonAutoConfiguration.class })
public class TimeAutoConfiguration {

}

