package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.dto.AccommodationCardDTO;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.rasto.accommodationbookingsystem.web.ui.components.AccommodationCard;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Route(value = "", layout = MainLayout.class)
public class AccommodationsView extends Div {

    private final int ACCOMMODATIONS_CARDS_PER_ROW = 3;

    private final HorizontalLayout accommodationsLayout = new HorizontalLayout();
    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationsView(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
        initAccommodationsLayout();
        add(accommodationsLayout);
    }

    private void initAccommodationsLayout() {
        List<AccommodationCardDTO> accommodationCards = accommodationService.findAllAccommodationsCards();
        List<AccommodationCard> cardList = accommodationCards.stream().map(this::createAccommodationCard).collect(Collectors.toList());

        accommodationsLayout.add(cardList.get(0), cardList.get(1), cardList.get(2));
    }

    private AccommodationCard createAccommodationCard(AccommodationCardDTO accommodationCardDTO) {
        AccommodationCard accommodationCard = new AccommodationCard();
        accommodationCard.setAccommodationId(accommodationCardDTO.getId());
        accommodationCard.setName(accommodationCardDTO.getName());
        accommodationCard.setPricePerNight(accommodationCardDTO.getPricePerNight());
        accommodationCard.setPhoto(accommodationCardDTO.getMainPhotoUrl());

        return accommodationCard;
    }

}
