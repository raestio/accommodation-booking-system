package com.rasto.accommodationbookingsystem.web.ui.components;

import com.rasto.accommodationbookingsystem.HasLogger;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasClickListeners;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;
import elemental.json.JsonArray;
import elemental.json.JsonValue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.rasto.accommodationbookingsystem.backend.constant.Constants.CURRENCY;
import static com.rasto.accommodationbookingsystem.backend.constant.Constants.DATE_FORMATTER_PATTERN;


@Tag("booking-form")
@HtmlImport("src/components/booking-form.html")
public class BookingForm extends PolymerTemplate<BookingForm.Model> implements HasLogger {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER_PATTERN);

    private String currency;
    private BigDecimal totalPrice;

    @Id("priceSpan")
    private Span priceSpan;

    @Id("book")
    private Button bookButton;

    @Id("totalTextSpan")
    private Span totalTextSpan;

    @Id("totalPriceSpan")
    private Span totalPriceSpan;

    public interface Model extends TemplateModel {
        String getDateFrom();
        String getDateTo();
        void setDateFrom(String dateFrom);
        void setDateTo(String dateTo);
    }

    public BookingForm() {
        bookButton.setEnabled(false);
        setTotalVisible(false);
        currency = CURRENCY;
    }

    public void setTotalVisible(boolean visible) {
        totalPriceSpan.setVisible(visible);
        totalTextSpan.setVisible(visible);
    }

    public void setOnBookButtonClickListener(OnBookButtonClickListener onBookButtonClickListener) {
        bookButton.addClickListener((ComponentEventListener<HasClickListeners.ClickEvent<Button>>) event -> {
            onBookButtonClickListener.onClick(getCheckInDate(), getCheckOutDate());
        });
    }

    public void setOnCheckOutDateChangeListener(OnCheckOutDateChangeListener onCheckOutDateChangeListener) {
        getElement().addPropertyChangeListener("dateTo", event -> {

            if (!event.getValue().toString().isEmpty() && !event.getValue().toString().equals(getModel().getDateFrom()) && isNowOrInFuture(getCheckInDate())) {
                setTotalVisible(true);
                bookButton.setEnabled(true);
                onCheckOutDateChangeListener.onChange(getCheckInDate(), getCheckOutDate());
            } else {
                setTotalVisible(false);
                bookButton.setEnabled(false);
                if (event.getValue().toString().equals(getModel().getDateFrom()) || !isNowOrInFuture(getCheckInDate())) {
                    clearDatePicker();
                }
            }
        });
    }

    private boolean isNowOrInFuture(LocalDate checkInDate) {
        return !checkInDate.isBefore(LocalDate.now());
    }

    public void setTotalPriceSpanText(String text) {
        totalPriceSpan.setText(text);
    }

    public void setTotalPrice(BigDecimal price) {
        totalPrice = price;
        totalPriceSpan.setText(price.toString() + " " + currency);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setPriceSpanText(String text) {
        priceSpan.setText(text);
    }

    public void setPrice(BigDecimal price) {
        priceSpan.setText(price.toString() + " " + currency);
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDisabledDays(List<LocalDate> disabledDays) {
        JsonValue jsonValue = elemental.json.Json.createArray();

        int index = 0;
        for (LocalDate localDate : disabledDays) {
            ((JsonArray) jsonValue).set(index, String.valueOf(localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()));
            index++;
        }

        getElement().setPropertyJson("disabledDays", jsonValue);
    }

    public void clearDatePicker() {
        getModel().setDateFrom("");
        getModel().setDateTo("");
    }

    public void disable() {
        bookButton.setEnabled(false);
    }

    public LocalDate getCheckInDate() {
        return LocalDate.parse(getModel().getDateFrom(), formatter);
    }

    public LocalDate getCheckOutDate() {
        return LocalDate.parse(getModel().getDateTo(), formatter);
    }

    public interface OnBookButtonClickListener {
        void onClick(LocalDate checkIn, LocalDate checkOut);
    }

    public interface OnCheckOutDateChangeListener {
        void onChange(LocalDate checkIn, LocalDate checkOut);
    }
}
