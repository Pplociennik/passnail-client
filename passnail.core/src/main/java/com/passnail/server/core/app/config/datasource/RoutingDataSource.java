package com.passnail.server.core.app.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Pszemko at piątek, 05.02.2021 02:21
 * Project: passnail-client
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    static final int DEFAULT = 0;
    static final int NEW = 1;

    private volatile int key = DEFAULT;

    void setKey(int key) {
        this.key = key;
    }

    private Map<Object, Object> dataSources = new HashMap();

    RoutingDataSource() {
        setTargetDataSources(dataSources);
    }

    void addDataSource(int key, DataSource dataSource) {
        dataSources.put(Integer.valueOf(key), dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return Integer.valueOf(key);
    }

    @Override
    protected DataSource determineTargetDataSource() {
        return (DataSource) dataSources.get(key);
    }

    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }
}
