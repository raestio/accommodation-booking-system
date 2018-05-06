package com.rasto.accommodationbookingsystem.backend.service.impl;

import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.service.ConvertingService;
import com.rasto.accommodationbookingsystem.backend.service.dto.AccommodationCardDTO;
import com.rasto.accommodationbookingsystem.backend.service.dto.PhotoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertingServiceImpl implements ConvertingService {

    private final ModelMapper modelMapper;

    @Autowired
    public ConvertingServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <D> D convert(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    @Override
    public AccommodationCardDTO convertToAccommodationCardDTO(Accommodation accommodation) {
        TypeMap<Accommodation, AccommodationCardDTO> typeMap = modelMapper.getTypeMap(Accommodation.class, AccommodationCardDTO.class);
        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(Accommodation.class, AccommodationCardDTO.class);
            typeMap.addMappings(mapping -> {
                mapping.map(Accommodation::getId, AccommodationCardDTO::setId);
                mapping.map(Accommodation::getName, AccommodationCardDTO::setName);
                mapping.map(Accommodation::getPricePerNight, AccommodationCardDTO::setPricePerNight);
                mapping.skip(AccommodationCardDTO::setPhoto);
            });
        }

        AccommodationCardDTO cardDTO = typeMap.map(accommodation);
        PhotoDTO photoDTO = modelMapper.map(accommodation.getPhotos().get(0), PhotoDTO.class);
        cardDTO.setPhoto(photoDTO);

        return cardDTO;
    }
}
