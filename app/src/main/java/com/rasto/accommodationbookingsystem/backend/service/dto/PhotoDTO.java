package com.rasto.accommodationbookingsystem.backend.service.dto;

import java.io.Serializable;

public class PhotoDTO implements Serializable {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
