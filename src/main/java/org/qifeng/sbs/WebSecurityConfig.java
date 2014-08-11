/**
 * 
 */
package org.qifeng.sbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author jackho
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.qifeng.sbs")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests() //set resources permitted.
				.antMatchers("/resources/**" , "/signup").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()  //set login page url.
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()     //set log out perimitted for all user.
				.permitAll();
	}
	@Autowired // set in-memory authentication 
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().
		withUser("user").password("pass").roles("USER").and().
		withUser("trader").password("pass").roles("USER","TRADER").and().
		withUser("admin").password("pass").roles("USER","TRADER","ADMIN");
		
	}
	
	
}
