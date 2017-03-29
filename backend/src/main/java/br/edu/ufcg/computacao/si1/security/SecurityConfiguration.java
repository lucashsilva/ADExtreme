package br.edu.ufcg.computacao.si1.security;


import br.edu.ufcg.computacao.si1.providers.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserAuthenticationProvider authProvider;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthEndPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/api/auth/login").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/users/buy").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/buy").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/advertisements*").permitAll()
                .antMatchers(HttpMethod.GET, "/*").permitAll()
                .antMatchers(HttpMethod.GET, "/advertisements/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEndPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);

    }

}