package com.mrlep.meon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
public class ConfigValue {
    private List<String> mapFileSupportTypes;
    @Value("${config.fileSupportTypes}")
    private String[] fileSupportTypes;
    @Value("${config.folder.files}")
    private String folderFiles;
    @Value("${config.folder.path.files}")
    private String folderPathFiles;
    @PostConstruct
    private void initSupportFileTypes() {
        this.mapFileSupportTypes = Arrays.asList(fileSupportTypes);
    }

    public String[] getFileSupportTypes() {
        return fileSupportTypes;
    }

    public void setFileSupportTypes(String[] fileSupportTypes) {
        this.fileSupportTypes = fileSupportTypes;
    }

    public List<String> getMapFileSupportTypes() {
        return mapFileSupportTypes;
    }

    public void setMapFileSupportTypes(List<String> mapFileSupportTypes) {
        this.mapFileSupportTypes = mapFileSupportTypes;
    }

    public String getFolderFiles() {
        return folderFiles;
    }

    public void setFolderFiles(String folderFiles) {
        this.folderFiles = folderFiles;
    }

    public String getFolderPathFiles() {
        return folderPathFiles;
    }

    public void setFolderPathFiles(String folderPathFiles) {
        this.folderPathFiles = folderPathFiles;
    }
}
