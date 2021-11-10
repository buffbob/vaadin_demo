package com.example.application.views;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value="dashboard", layout = MainLayout.class)
@PageTitle("dashboard | Vaadin CRM")
@PermitAll
public class Dashboard extends VerticalLayout {

    private final CrmService service;

    public Dashboard(CrmService service) {
        this.service = service;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getContactStats(), getCompaniesChart());
    }

    private Component getContactStats() {
        Span stats = new Span(service.countContacts() + " contacts");
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Component getCompaniesChart() {
        Chart c = new Chart(ChartType.PIE);
        DataSeries data = new DataSeries();
        service.findAllCompanies().forEach(company -> {
            data.add(new DataSeriesItem(company.getName(), company.getEmployeeCount()));
        });
        c.getConfiguration().setSeries(data);
        return c;
    }

}
