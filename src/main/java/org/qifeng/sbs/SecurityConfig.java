/**
 * 
 */
package org.qifeng.sbs;

import org.qifeng.sbs.controller.AccessDeniedExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author jackho
 *
 */
@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses=org.qifeng.sbs.service.UserServiceImpl.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired // set in-memory authentication 
	public void configureGlobal(UserDetailsService userDetailsService , AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService);
	}
	
	@Autowired
	AccessDeniedExceptionHandler accessDeniedExceptionHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() //set resources permitted.
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/error/**").permitAll()
				.antMatchers("/strategy/**").hasRole("ADMIN") // add authorization
				.anyRequest().authenticated()
				.and()
			.formLogin()  //set login page url.
				.loginPage("/login")
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
			.logout()     //set log out perimitted for all user.
				.permitAll()
				.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedExceptionHandler);
	}
//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().
//        withUser("user").password("password").roles("USER").and().
//        withUser("trader").password("password").roles("USER","TRADER").and().
//        withUser("admin").password("password").roles("USER", "TRADER", "ADMIN");
//    }
	
	
	
}
