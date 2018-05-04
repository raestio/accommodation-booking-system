package com.rasto.accommodationbookingsystem.backend.service;

import java.io.IOException;
import java.util.Map;

public interface ImageUploadService {

    /**
     * Upload image
     * @return url of image
     */
    String upload(Object file, Map options) throws IOException;
}
