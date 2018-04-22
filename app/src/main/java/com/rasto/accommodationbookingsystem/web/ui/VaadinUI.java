package com.rasto.accommodationbookingsystem.web.ui;

import com.rasto.accommodationbookingsystem.repository.entity.User;
import com.rasto.accommodationbookingsystem.service.CustomerService;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class VaadinUI extends UI {

    @Autowired
    private CustomerService customerService;
    final Grid<User> grid;

    public VaadinUI() {
        this.grid = new Grid<>(User.class);
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(grid);
        listCustomers();
    }

    private void listCustomers() {
        grid.setItems(customerService.listCustomers());
    }
}
