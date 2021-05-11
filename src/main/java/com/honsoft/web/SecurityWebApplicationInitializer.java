package com.honsoft.web;


import org.springframework.security.web.context.*;

import com.honsoft.web.config.SecurityConfig;

public class SecurityWebApplicationInitializer
      extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}