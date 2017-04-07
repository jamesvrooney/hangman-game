package com.rooney.james.validator;

import com.rooney.james.model.Player;
import com.rooney.james.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by jamesvrooney on 30/03/17.
 */
@Component
public class PlayerValidator implements Validator {
    @Autowired
    private PlayerService playerService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Player.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Player player = (Player) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (player.getUsername().length() < 6 || player.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (playerService.findByUsername(player.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (player.getPassword().length() < 8 || player.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!player.getPasswordConfirm().equals(player.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}
