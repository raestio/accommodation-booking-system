package com.rasto.accommodationbookingsystem.backend.service;

import java.io.IOException;
import java.io.InputStream;

public interface UtilityService {
    byte [] fromInputStreamToByteArray(InputStream inputStream) throws IOException;
}
