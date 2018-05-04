package com.rasto.accommodationbookingsystem.backend.service;

import java.io.IOException;
import java.util.Map;

public interface ImageUploadService {

    /**
     * Upload image
     * @param file image
     * @param options options
     * @return url of image
     * @throws IOException
     */
    String upload(Object file, Map options) throws IOException;

    /**
     * Upload image
     * @param file image
     * @return url of image
     * @throws IOException
     */
    String upload(Object file) throws IOException;
}
