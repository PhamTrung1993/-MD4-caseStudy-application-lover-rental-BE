package com.codegym.controller.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@PropertySource("classpath:application.properties")
public class ProviderController {
    @Autowired
    private Environment env;
}
