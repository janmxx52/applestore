//package com.example.AppleStore_Project_JavaSpringBoot_CoLy;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TestMail implements CommandLineRunner {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Override
//    public void run(String... args) throws Exception {
//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo("nguyenquochau080@gmail.com");
//        msg.setSubject("Test gửi mail");
//        msg.setText("Hello, mình là Hậu Dev, đang test gửi mail từ Spring Boot!");
//        mailSender.send(msg);
//
//        System.out.println("Gửi mail thành công!");
//    }
//}
//
