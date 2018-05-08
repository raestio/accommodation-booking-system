package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.data.entity.Photo;
import com.rasto.accommodationbookingsystem.backend.repository.PhotoRepository;
import com.rasto.accommodationbookingsystem.backend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo createNew() {
        return new Photo();
    }

    @Override
    public Photo createNew(String url) {
        return new Photo(url);
    }

    @Override
    public Photo createNew(String url, Accommodation accommodation) {
        return new Photo(url, accommodation);
    }

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    @Override
    public Optional<Photo> findById(Long id) {
        return photoRepository.findById(id);
    }

    @Override
    @Transactional
    public Photo saveOrUpdate(Photo entity) {
        return photoRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        photoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Photo entity) {
        photoRepository.delete(entity);
    }
}
