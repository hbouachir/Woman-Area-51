package tn.esprit.spring.womanarea51;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class Monitor {

    @Autowired
    private DataSource dataSource;


    @PostConstruct
    public void init() {

        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("quartz_tables.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
}
