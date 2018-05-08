package com.rasto.accommodationbookingsystem.web.ui.utils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;

import java.util.Optional;

import static com.rasto.accommodationbookingsystem.backend.constant.Constants.OK;

public class NotificationUtils {

    public static Notification createNotification(String labelMessage, Optional<UI> uiComponent) {
        Label label = new Label(labelMessage);
        Button button = new Button(OK);
        Notification notification = new Notification(label, button);
        button.addClickListener(e -> {
            notification.close();
            uiComponent.ifPresent(ui -> ui.getPage().reload());
        });
        notification.setPosition(Notification.Position.MIDDLE);
        return notification;
    }
}
