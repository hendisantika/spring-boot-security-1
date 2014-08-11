/**
 * 
 */
package org.qifeng.sbs;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * @author jackho
 *
 */
@Configuration
public class SpringContext extends WebMvcConfigurerAdapter{

	/************************  Spring Security  ****************************/
	// decide which folder to be exposed
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	// set the highest order to login page
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	/************************  End Spring Security  ****************************/
	/************************  Internationalization  ****************************/
	
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.TAIWAN);
        return slr;
    }
 
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        /*this interceptor will look for a request parameter named ‘lang’ and will 
         * use its value to determine which locale to switch to. */
        lci.setParamName("lang");
        /*For example, adding ‘lang=en’ to the end of any request will render 
         * the messages from default English locale’s message file.*/
        return lci;
    }
    //Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
    // set internationalization of messages
//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//    	ResourceBundleMessageSource source = new ResourceBundleMessageSource();
//    	source.setBasename("message");
//    	return source;
//    }
    /************************ End Internationalization  ****************************/
}
