package com.example.application.argo.views.list;

import com.example.application.argo.data.entity.Account;
import com.example.application.argo.data.service.CrmService;
import com.example.application.argo.views.MainLayout;
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
@Route(value="", layout = MainLayout.class)
@PageTitle("Accounts | Argo360 CRM")
@PermitAll
public class AccountsView extends VerticalLayout {
    Grid<Account> grid = new Grid<>(Account.class);
    TextField filterText = new TextField();
    CrmService service;

    public AccountsView(CrmService service) {
        this.service = service;
        addClassName("list-view");
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
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("companyName", "owner", "accountType", "phone","website");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        HorizontalLayout toolbar = new HorizontalLayout(filterText);
        toolbar.addClassName("toolbar");
        return toolbar;
    }


    private void updateList() {
        grid.setItems(service.findAllAccounts(filterText.getValue()));
    }


}
