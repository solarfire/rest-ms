package sco.co.solarsail.web.configuration;

import org.springframework.context.annotation.Configuration;
import sco.co.solarsail.model.Bean;

/**
 * Created by craigconnell on 06/12/2017.
 */
@Configuration
public class ContainerDataSource {

    @org.springframework.context.annotation.Bean
    public Bean testBean() {
        return new Bean(999L, "Spring Bean");
    }



}
