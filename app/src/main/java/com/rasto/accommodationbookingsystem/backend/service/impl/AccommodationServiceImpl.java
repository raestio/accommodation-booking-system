package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.data.entity.AccommodationType;
import com.rasto.accommodationbookingsystem.backend.data.entity.Address;
import com.rasto.accommodationbookingsystem.backend.data.entity.Photo;
import com.rasto.accommodationbookingsystem.backend.exception.AccommodationNotFoundException;
import com.rasto.accommodationbookingsystem.backend.repository.AccommodationRepository;
import com.rasto.accommodationbookingsystem.backend.service.*;
import com.rasto.accommodationbookingsystem.backend.service.dto.AccommodationCardDTO;
import com.rasto.accommodationbookingsystem.backend.service.dto.AccommodationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final ImageUploadService imageUploadService;
    private final PhotoService photoService;
    private final AddressService addressService;
    private final AccommodationTypesService accommodationTypesService;

    private ConvertingService convertingService;

    @Autowired
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, ImageUploadService imageUploadService, PhotoService photoService, AddressService addressService, AccommodationTypesService accommodationTypesService) {
        this.accommodationRepository = accommodationRepository;
        this.imageUploadService = imageUploadService;
        this.photoService = photoService;
        this.addressService = addressService;
        this.accommodationTypesService = accommodationTypesService;
    }

    @Override
    public List<AccommodationCardDTO> findAllAccommodationsCards()  {
        List<Accommodation> accommodations = accommodationRepository.findAll();

        return accommodations.stream().map(accommodation -> convertingService.convertToAccommodationCardDTO(accommodation)).collect(Collectors.toList());
    }

    @Override
    public BigDecimal computeTotalBookingPrice(LocalDate checkIn, LocalDate checkOut, Long accommodationId) throws AccommodationNotFoundException {
        Optional<Accommodation> accommodation = accommodationRepository.findById(accommodationId);
        if (!accommodation.isPresent()) {
            throw new AccommodationNotFoundException();
        }
        BigDecimal pricePerNight = accommodation.get().getPricePerNight();
        BigDecimal numOfDaysBetween = BigDecimal.valueOf(ChronoUnit.DAYS.between(checkIn, checkOut));
        return pricePerNight.multiply(numOfDaysBetween);
    }

    @Override
    @Transactional
    public Accommodation saveAccommodationWithPhotos(Accommodation accommodation, AccommodationType type, Address address, List<File> photos) throws IOException {
        List<Photo> photoEntities = new ArrayList<>();


        for (File file : photos) {
            String url = imageUploadService.upload(file.getAbsolutePath());
            photoEntities.add(photoService.createNew(url, accommodation));
        }

        //we must assign already defined accommodation type
        AccommodationType accommodationType = accommodationTypesService.findByName(type.getName()).get();
        accommodation.setType(accommodationType);

        //check if address exists already
        Optional<Address> addressOpt = addressService.findByAddress(address.getCountry(), address.getCity(), address.getStreet());
        if (addressOpt.isPresent()) {
            accommodation.setAddress(addressOpt.get());
        } else {
            accommodation.setAddress(address);
        }

        accommodation.setPhotos(photoEntities);
        return accommodationRepository.saveAndFlush(accommodation);
    }

    @Override
    public Accommodation createNew() {
        return new Accommodation();
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    @Transactional
    public Accommodation saveOrUpdate(Accommodation entity) {
        return accommodationRepository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Accommodation entity) {
        accommodationRepository.delete(entity);
    }

    @Override
    public List<Accommodation> findAnyMatching(Optional<String> filter) {
        if (filter.isPresent()) {
            String filt = "%" + filter.get() + "%";
            return accommodationRepository.findByCountryOrCityLikeIgnoreCase(filt);
        } else {
            return findAll();
        }
    }

    @Override
    public List<AccommodationCardDTO> findAnyMatchingAccommodationsCards(Optional<String> filter) {
        List<Accommodation> accommodations = findAnyMatching(filter);
        return accommodations.stream().map(accommodation -> convertingService.convertToAccommodationCardDTO(accommodation)).collect(Collectors.toList());
    }

    @Override
    public List<AccommodationDTO> findAllAndConvertToDTO() {
        List<Accommodation> accommodations = findAll();
        return accommodations.stream().map(accommodation -> convertingService.convert(accommodation, AccommodationDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<AccommodationDTO> findByIdAndConvertToDTO(Long id) {
        Optional<Accommodation> optionalAccommodation = findById(id);
        return optionalAccommodation.map(accommodation -> convertingService.convert(accommodation, AccommodationDTO.class));

    }

    @Autowired
    public void setConvertingService(ConvertingService convertingService) {
        this.convertingService = convertingService;
    }
}
