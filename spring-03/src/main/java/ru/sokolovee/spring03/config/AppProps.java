package ru.sokolovee.spring03.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "application")
public class AppProps {

    private FileLocale fileUrl;
    private Locale locale;

    public FileLocale getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(FileLocale fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
