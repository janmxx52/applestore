package com.example.AppleStore_Project_JavaSpringBoot_CoLy.service;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// EmailService.java
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderConfirmation(String to, Order order) {
        if (to == null || to.isBlank()) return;

        String subject = "[AppleStore] Xác nhận đơn hàng #" + order.getId();
        StringBuilder body = new StringBuilder();
        body.append("Xin chào ").append(order.getFullName()).append("\n\n");
        body.append("Cảm ơn bạn đã đặt hàng tại AppleStore.\n");
        body.append("Mã đơn: ").append(order.getId()).append("\n");
        body.append("Tổng tiền: ").append(order.getTotal()).append(" VND\n");
        body.append("Hình thức thanh toán: ").append(order.getPaymentType()).append("\n\n");
        body.append("Trân trọng,\nAppleStore");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body.toString());

        mailSender.send(msg);
    }
}
