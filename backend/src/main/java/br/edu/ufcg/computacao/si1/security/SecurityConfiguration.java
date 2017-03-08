package br.edu.ufcg.computacao.si1.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.edu.ufcg.computacao.si1.providers.UserAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	 private UserAuthenticationProvider authProvider;
	 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        http.headers().cacheControl();
        http.headers().frameOptions().disable();

        http.csrf().disable() // disable csrf for our requests.
            .authorizeRequests()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()
            .and()
            // We filter the api/login requests
            .addFilterBefore(new JWTLoginFilter("/api/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            // And filter other requests to check the presence of JWT in header
            .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
       
     }
  
}