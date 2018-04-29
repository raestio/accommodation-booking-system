package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.dto.AccommodationCardDTO;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.rasto.accommodationbookingsystem.web.ui.components.AccommodationCard;
import com.rasto.accommodationbookingsystem.web.ui.components.BoardLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Route(value = "", layout = MainLayout.class)
@RouteAlias(value = "accommodations", layout = MainLayout.class)
@Tag("accommodations-view")
@HtmlImport("src/views/accommodations-view.html")
public class AccommodationsView extends PolymerTemplate<TemplateModel> implements HasLogger {

    @Id("accommodationsBoardLayout")
    private BoardLayout accommodationsBoardLayout;

    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationsView(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
        accommodationsBoardLayout.setComponentsCountInRow(3);
        initAccommodationsCards();
    }

    private void initAccommodationsCards() {
        List<AccommodationCardDTO> accommodationCards = accommodationService.findAllAccommodationsCards();
        List<AccommodationCard> cardList = accommodationCards.stream().map(this::createAccommodationCard).collect(Collectors.toList());
        cardList.forEach(card -> accommodationsBoardLayout.add(card));
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
