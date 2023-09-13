package ru.aleksbratchin.TopTenBot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("bot") //https://www.baeldung.com/configuration-properties-in-spring-boot
public class BotConfig {
    private String name;
    private String token;
}
