package com.rooney.james.service;

/**
 * Created by jamesvrooney on 30/03/17.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
