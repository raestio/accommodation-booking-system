package com.rasto.accommodationbookingsystem.web.ui.views;

import com.rasto.accommodationbookingsystem.security.UserAuthenticationState;
import com.rasto.accommodationbookingsystem.web.ui.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "new-accommodation", layout = MainLayout.class)
@Tag("new-accommodation-view")
@HtmlImport("src/views/new-accommodation-view.html")
@PageTitle("New accommodation")
public class NewAccommodationView extends PolymerTemplate<TemplateModel> implements BeforeEnterObserver {

    private final UserAuthenticationState userAuthenticationState;

    @Id("accommodationName")
    private TextField accommodationName;

    @Id("numGuests")
    private TextField numGuests;

    @Id("numBeds")
    private TextField numBeds;

    @Id("numBaths")
    private TextField numBaths;

    @Id("country")
    private TextField country;

    @Id("city")
    private TextField city;

    @Id("street")
    private TextField street;

    @Id("pricePerNight")
    private TextField pricePerNight;

    @Id("upload")
    private Upload upload;

    @Id("createButton")
    private Button createButton;

    @Autowired
    public NewAccommodationView(UserAuthenticationState userAuthenticationState) {
        this.userAuthenticationState = userAuthenticationState;
        accommodationName.setAutofocus(true);
        createButton.setEnabled(false);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!userAuthenticationState.isLoggedUserAdmin()) {
            event.rerouteTo(AccommodationsView.class);
        }
    }
}
