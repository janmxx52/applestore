package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.User;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String ShowRegisterForm(Model model) {
        if(!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "Register";
    }

    @PostMapping({"/Register","/register"})
    public String processRegister(@Valid @ModelAttribute("user") User user,
                                  BindingResult result,
                                  Model model) {
            if(user.getConfirmPassword() == null || user.getConfirmPassword().isBlank()){
                result.rejectValue("confirmPassword", "confirmPassword.empty", "Vui lòng xác nhận mật khaẩu");
            }else if (user.getPassword() == null || !user.getPassword().equals(user.getConfirmPassword())) {
                result.rejectValue("confirmPassword","password.mismatch", "Mật khẩu xác nhận không khớp");
            }

        if (result.hasErrors()) {
            return "Register";
        }
        try {
            userService.register(user);
        }catch (Exception e) {
            String msg=e.getMessage();
            if("username_exist".equals(msg)) {
                result.rejectValue("username","username_exist", "Tên đăng nhập đã tồn tại");
                return "Register";
            }
            else  if("email_exist".equals(msg)) {
                result.rejectValue("email","email_exist","Email đã tồn tại");
                return "Register";
            }
            else {
                model.addAttribute("error", "Đăng ký thất bại do lỗi hệ thống. Vui lòng thử lại sau");
                    return "Register";
            }
        }
        return "Login";
    }
}
