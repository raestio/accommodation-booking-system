package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.backend.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService implements ImageUploadService, HasLogger {

    private final Cloudinary cloudinary;

    public CloudinaryService(@Value("${cloudinary.cloud_name}") String cloudName, @Value("${cloudinary.api_key}") String apiKey, @Value("${cloudinary.api_secret}") String apiSecret) {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret));
    }

    @Override
    public String upload(Object file, Map options) throws IOException {
        Map result = cloudinary.uploader().upload(file, options);
        return result.get("url").toString();
    }

    @Override
    public String upload(Object file) throws IOException {
        return upload(file, ObjectUtils.emptyMap());
    }
}
