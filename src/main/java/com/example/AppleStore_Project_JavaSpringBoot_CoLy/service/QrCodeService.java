// QrCodeService.java
package com.example.AppleStore_Project_JavaSpringBoot_CoLy.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Base64;

@Service
public class QrCodeService {

    public String generateBankQR(String bankAccount, String accountName,
                                 String bankName, BigDecimal amount, String content) {

        String payload = String.format("BANK|ACC:%s|NAME:%s|BANK:%s|AMOUNT:%s|CONTENT:%s",
                bankAccount, accountName, bankName,
                amount.toPlainString(), content);

        try {
            QRCodeWriter qrWriter = new QRCodeWriter();
            BitMatrix matrix = qrWriter.encode(payload, BarcodeFormat.QR_CODE, 350, 350);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                MatrixToImageWriter.writeToStream(matrix, "PNG", baos);
                String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
                return "data:image/png;base64," + base64;
            }
        } catch (WriterException | java.io.IOException e) {
            throw new RuntimeException("Không tạo được QR", e);
        }
    }
}
