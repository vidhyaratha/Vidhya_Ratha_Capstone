package org.vidhyaratha.employeeassetmanagement.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AssetNotFoundException.class)
    public String handleAssetNotFoundException(AssetNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }


    @ExceptionHandler(DeviceExceededLimitationException.class)
    public String handleDeviceExceededLimitationException(DeviceExceededLimitationException ex, Model model)
    {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }


    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(PasswordMatchException.class)
    public String handleUserNotFoundException(PasswordMatchException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }


    @ExceptionHandler(AccessRoleException.class)
    public String handleAccessRoleException(AccessRoleException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }
}