package com.shaadi.shaadi.Services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.shaadi.shaadi.Config.AppProperties;
import com.shaadi.shaadi.Services.AdminAuthService;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {

    private final AppProperties appProperties;

    @Override
    public boolean login(String username, String password) {
        String cfgUser = appProperties.getAdmin().getUsername();
        String cfgPass = appProperties.getAdmin().getPassword();
        return cfgUser != null && cfgPass != null && cfgUser.equals(username) && cfgPass.equals(password);
    }
}