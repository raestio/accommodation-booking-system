package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.dto.AccommodationCardDTO;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.rasto.accommodationbookingsystem.web.ui.components.AccommodationCard;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "accommodations", layout = MainLayout.class)
public class AccommodationsView extends Div implements HasLogger {

    private final int ACCOMMODATIONS_CARDS_PER_ROW = 3; //TODO create custom component

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
        accommodationCard.addClickListener(event -> event.getSource().getUI().ifPresent(ui -> ui.navigate("accommodations/" + event.getSource().getAccommodationId())));
        return accommodationCard;
    }

}
