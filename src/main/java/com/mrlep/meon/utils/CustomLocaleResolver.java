package com.mrlep.meon.utils;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CustomLocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
    private final Locale LOCALE_EN = new Locale("en");
    private final Locale LOCALE_VI = new Locale("vi");

    private String defaultLanguage;

    public CustomLocaleResolver(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        List<Locale> locales = Arrays.asList(LOCALE_EN, LOCALE_VI);
        Locale defaultLocale = Locale.lookup(Locale.LanguageRange.parse(defaultLanguage), locales);
        String defaultLanguage = request.getHeader("Accept-Language");
        if (StringUtils.isNullOrEmpty(defaultLanguage) || (!LOCALE_EN.getLanguage().equals(defaultLanguage)
                && !LOCALE_VI.getLanguage().equals(defaultLanguage))) {
            defaultLanguage = request.getParameter("language");
            if (StringUtils.isNullOrEmpty(defaultLanguage)) {
                return defaultLocale;
            }

        }

        List<Locale.LanguageRange> list = Locale.LanguageRange.parse(defaultLanguage);
        Locale locale = Locale.lookup(list, locales);
        if (locale == null) {
            locale = defaultLocale;
        }
        return locale;
    }

}
