package ru.mail.knhel7.spring_boot_conditional_app.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mail.knhel7.spring_boot_conditional_app.profiles.DevProfile;
import ru.mail.knhel7.spring_boot_conditional_app.profiles.ProductionProfile;
import ru.mail.knhel7.spring_boot_conditional_app.profiles.SystemProfile;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true")
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false")
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "")
    public SystemProfile defaultProfile() {
        return new ProductionProfile();
    }

}
