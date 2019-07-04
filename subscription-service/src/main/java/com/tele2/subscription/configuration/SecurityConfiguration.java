package com.tele2.subscription.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	log.debug("Setting security data");
    	auth.inMemoryAuthentication()
    	.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
    	.and()
		.withUser("john").password(passwordEncoder().encode("passw0rd")).roles("USER")
		.and()
		.withUser("jane").password(passwordEncoder().encode("passw0rd")).roles("PREMIUM");
		
		
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	// @formatter:off
    	httpSecurity
			.authorizeRequests()
			.antMatchers("/api/**")
				.authenticated()
			.antMatchers("/**")
				.permitAll()
			.and()
				.httpBasic()
			.and()
				.csrf()
				.disable();
    	// @formatter:on
    }
    
    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
  
}
