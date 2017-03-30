package com.rooney.james.controller;

import com.rooney.james.model.Game;
import com.rooney.james.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Controller
public class LoginController {

    private GameService gameService;

    @Autowired
    public LoginController() {

    }

    @GetMapping("/login")
    public String login(ModelMap modelMap) {

        return "login";
    }
}
