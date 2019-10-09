package sco.co.solarsail.dao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import sco.co.solarsail.model.Bean;

/**
 * Created by craigconnell on 06/12/2017.
 */
@Configuration
@ComponentScan("sco.solarsail.dao.config")
public class DaoConfiguration {

    @Autowired
    private Bean bean;


}
