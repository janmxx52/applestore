package com.example.AppleStore_Project_JavaSpringBoot_CoLy.config;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.util.MoneyUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean("money")
    public MoneyUtil moneyUtil() {
        return new MoneyUtil();
    }
}
