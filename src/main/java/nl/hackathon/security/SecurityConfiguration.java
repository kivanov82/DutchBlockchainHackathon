package nl.hackathon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by kivanov on 31/08/2016.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .httpBasic().and()
                .formLogin().loginPage("/").and()
                .authorizeRequests()
                .antMatchers("/index.html", "/fonts/*", "/bower_components/**", "/src/**", "/").permitAll()

                .anyRequest().authenticated()
                .and()
                .logout()
                .and()
                .csrf().disable();
        // @formatter:on
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("fan").password("password").roles("FAN")
                .and()
                .withUser("musician").password("password").roles("MUSICIAN");
    }

}
