package io.proj3ct.Test1SpringDemoBot.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Data
@ConfigurationProperties(prefix = "app.monk.adapter.telegram")
public class BotConfig {

    @NotBlank
    String botName;

    @NotBlank
    String token;

    @NotBlank
    Long ownerId;
}
