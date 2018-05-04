package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.rasto.accommodationbookingsystem.backend.service.UtilityService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class UtilityServiceImpl implements UtilityService {
    private final static int BLOCK_SIZE = 2048;

    @Override
    public byte[] fromInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte[] data = new byte[BLOCK_SIZE];

        int readBytes;
        while ((readBytes = inputStream.read(data, 0, data.length)) != -1) {
            outputStream.write(data, 0, readBytes);
        }
        outputStream.flush();

        return outputStream.toByteArray();
    }
}
