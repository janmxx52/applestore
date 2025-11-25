package com.example.AppleStore_Project_JavaSpringBoot_CoLy.service;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.User;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            // message sẽ chứa tên constraint hoặc tên cột
            String msg = ex.getMessage();

            // tuỳ DB/constraint, em chỉnh lại chuỗi điều kiện cho đúng
            if (msg != null && msg.contains("users.username")) {
                throw new RuntimeException("username_exist");
            }
            if (msg != null && msg.contains("users.email")) {
                throw new RuntimeException("email_exist");
            }

            // nếu không thuộc 2 loại trên, quăng lại cho trên xử lý
            throw ex;
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    //matches dung để so sánh password từ mysql qua getPassword của passwordEncoder trùng nhau thì return về Optional
    public Optional<User> authenticate(String username, String password) {
        if( username == null || password == null ) {
            return Optional.empty();

        }
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isPresent()){
            User user = opt.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
