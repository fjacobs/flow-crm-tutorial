package com.example.application.argo.views.list;

import com.example.application.argo.data.entity.Account;
import com.example.application.argo.data.entity.Asset;
import com.example.application.argo.data.entity.Lot;
import com.example.application.argo.data.service.CrmService;
import com.example.application.argo.views.MainLayout;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Component
@Scope("prototype")
@Route(value="", layout = MainLayout.class)
@PageTitle("Assets | Argo360 Makor (Backup) Inventory ")
@PermitAll
public class AssetsView extends VerticalLayout {
    Grid<Asset> grid = new Grid<>(Asset.class);
    TextField filterText = new TextField();
    CrmService service;

    public AssetsView(CrmService service) {
        this.service = service;
        addClassName("assets-view");
        setSizeFull();
        configureGrid();

        FlexLayout content = new FlexLayout(grid);
        content.setFlexGrow(2, grid);

        content.addClassNames("content", "gap-m");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
    }

    private void configureGrid() {
        grid.addClassNames("assets-grid");
        grid.setSizeFull();
        grid.setColumns("lot", "status", "clazz", "mfg","model","partNumber", "partNumber", "grade", "channel"
            ,"upgradeDescription", "auditDescription", "functionalDescription", "cosmeticsDescription",
            "onShelf", "pallet", "wareHouse", "location"
        );

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by Account...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("assets-toolbar");
        return toolbar;
    }


    private void updateList() {
        grid.setItems(service.findAllAssets(filterText.getValue()));
    }


}
