package com.example.AppleStore_Project_JavaSpringBoot_CoLy.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class QrCodeServiceTest {

    private final QrCodeService qrCodeService = new QrCodeService();

    @Test
    @DisplayName("TC_QR_01 - Tạo QR chuyển khoản thành công")
    void generateBankQR_success() {

        String bankAccount = "0123456789";
        String accountName = "HAU DEV";
        String bankName = "Vietcombank";
        BigDecimal amount = BigDecimal.valueOf(1500000);
        String content = "ORDER_1001";

        String qrBase64 = qrCodeService.generateBankQR(
                bankAccount,
                accountName,
                bankName,
                amount,
                content
        );

        assertNotNull(qrBase64);
        assertTrue(qrBase64.startsWith("data:image/png;base64,"));
        assertTrue(qrBase64.length() > 100);
    }
}
