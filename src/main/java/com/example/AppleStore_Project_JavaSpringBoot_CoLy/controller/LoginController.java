package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.User;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserService  userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //session dùng để lưu user đăng nhập

    @PostMapping("/login")
    public String doLogin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Model model,
                          HttpSession session){
        Optional<User> opt = userService.authenticate(username, password);
        if(opt.isPresent()){
            User user = opt.get();
            session.setAttribute("username", user.getUsername());
            session.setAttribute("password", user.getPassword());
            System.out.println(session.getAttribute("username"));
            return "redirect:/";
        }
        model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
        return "login";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("logout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
