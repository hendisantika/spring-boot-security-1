package org.qifeng.sbs;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Hello world!
 *
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({"org.qifeng.sbs.controller","org.qifeng.sbs"}) //org.qifeng.sbs => ThymeleafConfig / BeanConfig
public class App
{	
    public static void main( String[] args ){
       SpringApplication.run(App.class, args);     
    }   
    
    
    
   

}
