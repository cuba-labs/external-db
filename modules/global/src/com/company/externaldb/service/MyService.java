package com.company.externaldb.service;


import java.util.List;
import java.util.Map;

public interface MyService {
    String NAME = "exdb_MyService";

    List<Map<String, Object>> loadData();
}