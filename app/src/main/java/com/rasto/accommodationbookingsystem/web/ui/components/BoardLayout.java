package com.rasto.accommodationbookingsystem.web.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("board-layout")
@HtmlImport("src/components/board-layout.html")
public class BoardLayout extends PolymerTemplate<TemplateModel> {

    private final int DEFAULT_COMPONENTS_COUNT_IN_ROW = 6;
    private int componentsInRow = DEFAULT_COMPONENTS_COUNT_IN_ROW;

    @Id("verticalLayout")
    private VerticalLayout verticalLayout;

    private FlexComponent.JustifyContentMode justifyContentMode = FlexComponent.JustifyContentMode.CENTER;
    private FlexLayout flexLayout;
    private int componentsCount = 0;

    public void setComponentsCountInRow(int componentsInRow) {
        this.componentsInRow = componentsInRow;
    }

    public void add(Component component) {
        if (componentsCount % componentsInRow == 0) {
            flexLayout = createFlexLayout();
            verticalLayout.add(flexLayout);
        }

        flexLayout.add(component);
        componentsCount++;
    }

    private FlexLayout createFlexLayout() {
        FlexLayout flexLayout = new FlexLayout();
        flexLayout.setSizeFull();
        flexLayout.setJustifyContentMode(justifyContentMode);
        return flexLayout;
    }

    public void setJustifyContentMode(FlexComponent.JustifyContentMode justifyContentMode) {
        this.justifyContentMode = justifyContentMode;
    }

    public void clear() {
        componentsCount = 0;
        verticalLayout.removeAll();
    }
}
