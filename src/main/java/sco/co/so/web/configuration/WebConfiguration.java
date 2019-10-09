package sco.co.so.web.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import sco.co.so.service.configuration.ServiceConfiguration;
import sco.co.so.dao.config.DaoConfiguration;

/**
 * Created on 29/10/2017.
 * This replaces the SpringApplicationContext.xml file.
 *  1. SpringApplicationContext.xml 	NOW     @Configuration
 *      Treat as the configuration file for Spring MVC-enabled applications.
 *
 *  2. <context:component-scan/>	    NOW     @ComponentScan()
 *      Scan starts from base package and registers all controllers, repositories, service, beans, etc.
 *
 *  3. <mvc:annotation-driven/>   	    NOW     @EnableWebMvc
 *      Enable Spring MVC-specific annotations like @Controller
 */
@Configuration
@ComponentScan(basePackages = {"sco.co.so.web"})
@EnableWebMvc
@Import({ServiceConfiguration.class, DaoConfiguration.class})
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
