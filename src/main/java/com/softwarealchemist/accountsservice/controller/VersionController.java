package com.softwarealchemist.accountsservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VersionController {

    @Value("${build.version}")
    private String version;

    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.ok().body(version);
    }
}
