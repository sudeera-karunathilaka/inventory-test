package au.com.sudeera.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//Authentication
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication()
		.withUser("user").password("{noop}123").authorities("ROLE_USER")
		.and()
		.withUser("admin").password("{noop}123").authorities("ROLE_ADMIN", "ROLE_USER");
	}
	
	//Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().cacheControl();
		http.cors().and().csrf().disable()
		.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		.and()
		.authorizeRequests()
		.antMatchers("/api/inventory*").hasRole("USER")
		.antMatchers("/api/inventory/items*").hasRole("ADMIN")
        .antMatchers("/", "/v2/api-docs","/swagger-ui.html", "/webjars/**", "/favicon.ico").permitAll()
		.and()
		.httpBasic();
	}

}
