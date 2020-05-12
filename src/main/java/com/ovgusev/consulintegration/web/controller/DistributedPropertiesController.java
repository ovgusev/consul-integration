package com.ovgusev.consulintegration.web.controller;

import com.ovgusev.consulintegration.config.properties.MyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DistributedPropertiesController {
    @Value("${my.prop}")
    private String value;

    private final MyProperties myProperties;

    @GetMapping("/getPropFromValue")
    public String getPropFromValue() {
        return value;
    }

    @GetMapping("/getPropFromProperties")
    public String getPropFromProperties() {
        return myProperties.getProp();
    }
}
