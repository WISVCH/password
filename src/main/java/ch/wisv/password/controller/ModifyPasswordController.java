package ch.wisv.password.controller;

import ch.wisv.password.model.ModifyPasswordRequest;
import ch.wisv.password.service.ModifyPasswordService;
import ch.wisv.password.validator.ModifyPasswordRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Modify password controller
 */
@Controller
public class ModifyPasswordController {

    @Autowired
    private ModifyPasswordService modifyPasswordService;
    @Autowired
    private ModifyPasswordRequestValidator modifyPasswordRequestValidator;

    @InitBinder("modifyPasswordRequest")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(modifyPasswordRequestValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getEvents(Model model, @ModelAttribute String message) {
        model.addAttribute("modifyPasswordRequest", new ModifyPasswordRequest());
        return "form";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String createEvent(@Valid @ModelAttribute ModifyPasswordRequest modifyPasswordRequest,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        ModifyPasswordService.Result result = modifyPasswordService.modifyPassword(
                modifyPasswordRequest.getUsername(), modifyPasswordRequest.getCurrentPassword(),
                modifyPasswordRequest.getNewPassword1());

        switch (result) {
            case SUCCESS:
                redirectAttributes.addFlashAttribute("message", "change.success");
                return "redirect:/";
            case INVALID_CREDENTIALS:
                bindingResult.rejectValue("currentPassword", "currentPassword.invalid");
                break;
            case ERROR:
                bindingResult.reject("change.error");
                break;
        }
        return "form";
    }
}
