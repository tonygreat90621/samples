package com.example.demo.config.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return "ds1";
    }
}
