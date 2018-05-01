package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.security.UserAuthenticationState;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "bookings", layout = MainLayout.class)
@Tag("bookings-view")
@HtmlImport("src/views/bookings-view.html")
public class UserBookingsView extends PolymerTemplate<TemplateModel> implements BeforeEnterObserver {


    private final UserAuthenticationState userAuthenticationState;

    @Autowired
    public UserBookingsView(UserAuthenticationState userAuthenticationState) {
        this.userAuthenticationState = userAuthenticationState;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!userAuthenticationState.isAuthenticated()) {
            event.rerouteTo(AccommodationsView.class);
        }
    }
}
