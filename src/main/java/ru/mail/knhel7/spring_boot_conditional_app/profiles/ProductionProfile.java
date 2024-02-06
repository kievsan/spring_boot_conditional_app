package ru.mail.knhel7.spring_boot_conditional_app.profiles;

public class ProductionProfile implements SystemProfile {

    @Override
    public String getProfile() {
        return "Current profile is production";
    }
}
