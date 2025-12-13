package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Order;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

// InvoicePdfController.java
@Controller
public class InvoicePdfController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/invoice/pdf")
    public ResponseEntity<byte[]> invoicePdf(@RequestParam Long orderId) throws IOException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Ở đây em có thể reuse code PDFBox của thầy
        // hoặc đơn giản là sinh PDF basic: tên shop, info khách, bảng items, tổng tiền

        // TODO: chỗ này em copy logic từ code thầy, chỉ cần đổi từ req -> order

        byte[] pdfBytes = "PDF chưa được xây dựng".getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "invoice_" + orderId + ".pdf");

        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }
}

