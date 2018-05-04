package com.rasto.accommodationbookingsystem.backend.service;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.data.entity.Photo;

public interface PhotoService extends CrudService<Photo, Long> {
    Photo createNew(String url);
    Photo createNew(String url, Accommodation accommodation);
}
