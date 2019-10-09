package sco.co.solarsail.web.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by craigconnell on 31/10/2017.
 * By implementing {@code WebApplicationInitializer} we replace web.xml
 * Here we provide our {@link WebConfiguration} class and add DispatcherServlet, which acts as the FrontController of the Spring MVC application.
 * {@link WebConfiguration} class is the source of Spring beans, before which we used contextConfiglocation.
 */
public class WebServletConfiguration implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebConfiguration.class);
        webContext.setServletContext(servletContext);

        System.out.println("\n\n\n*****************\nonStartup\n\n\n");
        // Manage the life-cycle of the root application context
        //servletContext.addListener(new ContextLoaderListener(webContext)); //new

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");


        //addFilter(servletContext);
        /////////

        //addSiteMeshFilter(servletContext);
    }


    private class TestFilter implements Filter{
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            System.out.println("\n\n\n*****************\ndoFilter\n\n\n");
            filterChain.doFilter(servletRequest, servletResponse);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("\n\n\n*****************\ninit\n\n\n");
        }

        @Override
        public void destroy() {
            System.out.println("\n\n\n*****************\ndestroy\n\n\n");
        }
    }


}

