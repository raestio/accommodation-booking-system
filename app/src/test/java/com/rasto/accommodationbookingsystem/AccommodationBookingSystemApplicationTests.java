package com.rasto.accommodationbookingsystem;

import com.rasto.accommodationbookingsystem.backend.data.AccommodationTypeEnum;
import com.rasto.accommodationbookingsystem.backend.data.entity.Accommodation;
import com.rasto.accommodationbookingsystem.backend.data.entity.AccommodationType;
import com.rasto.accommodationbookingsystem.backend.data.entity.Address;
import com.rasto.accommodationbookingsystem.backend.data.entity.Photo;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationTypesService;
import com.rasto.accommodationbookingsystem.backend.service.AddressService;
import com.rasto.accommodationbookingsystem.backend.service.PhotoService;
import com.rasto.accommodationbookingsystem.backend.service.dto.AccommodationDTO;
import com.rasto.accommodationbookingsystem.backend.service.dto.PhotoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccommodationBookingSystemApplicationTests {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccommodationService accommodationService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private AccommodationTypesService accommodationTypesService;

	@Autowired
	private PhotoService photoService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void convertAccommodationEntityToAccommodationDTO() {
		Accommodation accommodation = accommodationService.createNew();
		accommodation.setId(1L);
		accommodation.setName("Testing name");
		accommodation.setGuests(5);
		accommodation.setBeds(3);
		accommodation.setBathrooms(54);
		accommodation.setPricePerNight(BigDecimal.valueOf(123456));

		AccommodationType accommodationType = accommodationTypesService.createNew();
		accommodationType.setName(AccommodationTypeEnum.BUNGALOW);

		Address address = addressService.createNew();
		address.setCountry("Test country");
		address.setCity("City");
		address.setStreet("street");

		accommodation.setAddress(address);
		accommodation.setType(accommodationType);

		Photo photo1 = photoService.createNew("tmp url");
		Photo photo2 = photoService.createNew("tmp url2");
		accommodation.setPhotos(Stream.of(photo1, photo2).collect(Collectors.toList()));

		AccommodationDTO accommodationDTO = modelMapper.map(accommodation, AccommodationDTO.class);
		assertEquals(accommodation.getId(), accommodationDTO.getId());
		assertEquals(accommodation.getBathrooms(), accommodationDTO.getBathrooms());
		assertEquals(accommodation.getBeds(), accommodationDTO.getBeds());
		assertEquals(accommodation.getGuests(), accommodationDTO.getGuests());
		assertEquals(accommodation.getName(), accommodationDTO.getName());
		assertEquals(accommodation.getPricePerNight(), accommodationDTO.getPricePerNight());
		assertEquals(accommodation.getType().getName().name(), accommodationDTO.getType().name());
		assertEquals(accommodation.getAddress().getCountry(), accommodationDTO.getAddress().getCountry());
		assertEquals(accommodation.getAddress().getCity(), accommodationDTO.getAddress().getCity());
		assertEquals(accommodation.getAddress().getStreet(), accommodationDTO.getAddress().getStreet());
		assertThat(accommodation.getPhotos().stream().map(Photo::getUrl).collect(Collectors.toList()), is(accommodationDTO.getPhotos().stream().map(PhotoDTO::getUrl).collect(Collectors.toList())));
	}
}
