package com.rooney.james.controller;

import com.rooney.james.model.Player;
import com.rooney.james.service.PlayerService;
import com.rooney.james.service.SecurityService;
import com.rooney.james.validator.PlayerValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jamesvrooney on 22/03/17.
 */
@Controller
public class LoginController {

    private PlayerService playerService;
    private SecurityService securityService;
    private PlayerValidator playerValidator;

    public LoginController(PlayerService playerService,
                           SecurityService securityService,
                           PlayerValidator playerValidator) {
        this.playerService = playerService;
        this.securityService = securityService;
        this.playerValidator = playerValidator;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("playerForm", new Player());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("playerForm") Player playerForm, BindingResult bindingResult, Model model) {
        playerValidator.validate(playerForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        playerService.save(playerForm);

        securityService.autologin(playerForm.getUsername(), playerForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    /*@RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }*/

    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}
