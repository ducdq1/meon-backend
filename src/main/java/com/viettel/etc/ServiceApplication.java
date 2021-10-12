package com.viettel.etc;

import com.viettel.etc.utils.CustomLocaleResolver;
import com.viettel.etc.utils.LoggingUtils;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@SpringBootApplication
@EnableJpaAuditing
//@EnableTransactionManagement
public class ServiceApplication {
    private static final Logger LOGGER = Logger.getLogger(ServiceApplication.class);

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("messages");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new CustomLocaleResolver("vi");
    }

    public static void main(String[] args) {

        LoggingUtils.setLogger(LoggerFactory.getLogger("f0_management_service"));
        LoggingUtils.setFunctionCode("F0ManagementService");
        SpringApplication.run(ServiceApplication.class, args);
    }

}
