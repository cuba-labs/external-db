package com.company.externaldb.web.screens;

import com.company.externaldb.service.MyService;
import com.haulmont.cuba.gui.components.AbstractWindow;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class Screen extends AbstractWindow {

    @Inject
    private MyService myService;

    public void loadData() {
        List<Map<String, Object>> rows = myService.loadData();
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> row : rows) {
            sb.append("Id: ").append(row.get("ID")).append(", ");
            sb.append("Name: ").append(row.get("NAME")).append("\n");
        }
        showNotification(sb.toString());
    }
}