package com.company.externaldb.service;

import com.haulmont.bali.db.MapListHandler;
import com.haulmont.bali.db.QueryRunner;
import com.haulmont.cuba.core.sys.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service(MyService.NAME)
public class MyServiceBean implements MyService, AppContext.Listener {

    @Resource(name = "myDataSource")
    private DataSource myDataSource;

    private Logger log = LoggerFactory.getLogger(MyServiceBean.class);

    public MyServiceBean() {
        AppContext.addListener(this);
    }

    @Override
    public List<Map<String, Object>> loadData() {
        log.info("Loading data from external DB");
        List<Map<String, Object>> rows;
        QueryRunner queryRunner = new QueryRunner(myDataSource);
        try {
            rows = queryRunner.query("select * from FOO", new MapListHandler());
        } catch (SQLException e) {
            throw new RuntimeException("Error loading data from external DB", e);
        }
        return rows;
    }

    @Override
    public void applicationStarted() {
        log.info("Initializing external DB");
        QueryRunner queryRunner = new QueryRunner(myDataSource);
        try {
            queryRunner.update("create table FOO (ID integer not null primary key, NAME varchar(100))");
            queryRunner.batch("insert into FOO (ID, NAME) values (?, ?)", new Object[][] {{1, "aaa"}, {2, "bbb"}});
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing external DB", e);
        }
    }

    @Override
    public void applicationStopped() {
    }
}