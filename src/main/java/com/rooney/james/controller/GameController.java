package com.rooney.james.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Controller
public class GameController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
