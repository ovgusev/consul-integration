package com.ovgusev.consulintegration.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class DiscoveryClientController {
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private final DiscoveryClient discoveryClient;

    @GetMapping("/discoveryClientPing")
    public String currentAppPing(@Value("${spring.application.name}") String appName) throws ServiceUnavailableException {
        URI service = serviceUrl(appName)
                .map(uri -> uri.resolve("/ping"))
                .orElseThrow(ServiceUnavailableException::new);
        return restTemplate.getForEntity(service, String.class)
                .getBody();
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    private Optional<URI> serviceUrl(String serviceId) {
        return discoveryClient.getInstances(serviceId)
                .stream()
                .findFirst()
                .map(ServiceInstance::getUri);
    }
}
