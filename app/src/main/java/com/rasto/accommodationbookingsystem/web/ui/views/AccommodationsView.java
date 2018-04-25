package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.backend.service.AccommodationService;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "", layout = MainLayout.class)
public class AccommodationsView extends Div {

    private final Board accommodationsBoard = new Board();
    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationsView(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

}
