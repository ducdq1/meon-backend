package com.mrlep.meon;

import com.mrlep.meon.firebase.FirebaseFirestore;
import com.mrlep.meon.firebase.FirestoreBillManagement;
import com.mrlep.meon.utils.CustomLocaleResolver;
import com.mrlep.meon.utils.LoggingUtils;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.LocaleResolver;

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

    public static void main(String[] args) throws Exception {

        LoggingUtils.setLogger(LoggerFactory.getLogger("MeOnService"));
        LoggingUtils.setFunctionCode("MeOnService");
        SpringApplication.run(ServiceApplication.class, args);
        FirebaseFirestore.getDb();
    }

}
