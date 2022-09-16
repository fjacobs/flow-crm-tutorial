package com.example.application.argo.views.list;

import com.example.application.argo.data.entity.Asset;
import com.example.application.argo.data.service.CrmService;
import com.example.application.argo.views.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
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


@Component
@Scope("prototype")
@Route(value = "", layout = MainLayout.class)
@PageTitle("Assets | Argo360 Makor (Backup) Inventory ")
@PermitAll
public class AssetsView extends VerticalLayout {
    Grid<Asset> grid = new Grid<>(Asset.class);
    TextField modelFilterText = new TextField();

    ComboBox<String> accountFilterComboBox = new ComboBox<>();
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
        getAllAssets();
    }

    private void getAllAssets() {
        service.findAllAssets();
    }

    private void configureGrid() {
        grid.addClassNames("assets-grid");
        grid.setSizeFull();
        grid.setColumns("lot", "grade", "status", "clazz", "mfg", "model", "partNumber", "serial", "channel", "onShelf", "pallet", "wareHouse", "location"
                , "functionalDescription", "cosmeticsDescription", "upgradeDescription", "auditDescription"
        );
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {

        accountFilterComboBox.setItems(service.getAllAccountNames());
        accountFilterComboBox.setPlaceholder("Select Account");
        accountFilterComboBox.addValueChangeListener(e-> accountFilterChangeListener(e.getValue()));

        modelFilterText.setPlaceholder("Filter by Model...");
        modelFilterText.setClearButtonVisible(true);
        modelFilterText.setValueChangeMode(ValueChangeMode.LAZY);
        modelFilterText.addValueChangeListener(e -> modelFilterEventListener());

        HorizontalLayout toolbar = new HorizontalLayout(accountFilterComboBox, modelFilterText);
        toolbar.addClassName("assets-toolbar");
        return toolbar;
    }


    private void modelFilterEventListener() {
        //  grid.setItems(service.findAllAssets("Coolblue"));
    }

    private void accountFilterChangeListener(String company) {
        System.out.println("User selected company: " + company);
        grid.setItems(service.findAssetsByAccount(accountFilterComboBox.getValue()));
    }
}
