package com.skilldistillery.enginex.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // this you get for free when you configure the db connection in application.properties file
    @Autowired
    private DataSource dataSource;

    // this bean is created in the application starter class if you're looking for it
    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()     // will hit the OPTIONS on the route
        .antMatchers(HttpMethod.GET, "/api/jobs/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.GET, "/api/jobstatus/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.GET, "/api/comments/**").permitAll() // Requests for our REST API must be authorized.       
        .antMatchers(HttpMethod.GET, "/api/jobtypes/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.GET, "/api/jobdetails/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.GET, "/api/skills/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.GET, "/api/apps/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.GET, "/api/users").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.GET, "/api/users/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.GET, "/api/educations/**").permitAll() // Requests for our REST API must be authorized.
       
        .antMatchers(HttpMethod.GET, "/api/userapps").permitAll() // Requests for our REST API must be authorized.
//      .antMatchers(HttpMethod.PUT, "/api/appdecision/**").permitAll() // Requests for our REST API must be authorized.
//      .antMatchers(HttpMethod.DELETE, "/api/userapps/**").permitAll() // Requests for our REST API must be authorized.

//        .antMatchers(HttpMethod.POST, "/api/userjobs/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.PUT, "/api/userjobs/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.DELETE, "/api/userjobs/**").permitAll() // Requests for our REST API must be authorized.

        .antMatchers(HttpMethod.POST, "/api/jobs").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.PUT, "/api/jobs/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.DELETE, "/api/jobs/**").permitAll() // Requests for our REST API must be authorized.
        
        .antMatchers(HttpMethod.POST, "/api/apps/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.PUT, "/api/users/**").permitAll() // Requests for our REST API must be authorized.
        .antMatchers(HttpMethod.DELETE, "/api/users/**").permitAll() // Requests for our REST API must be authorized.
        
        .antMatchers(HttpMethod.POST, "/api/comments/**").permitAll() // Requests for our REST API must be authorized.
        
        
        
        .antMatchers("/api/**").authenticated() // Requests for our REST API must be authorized.
        .anyRequest().permitAll()               // All other requests are allowed without authorization.
        .and()
        .httpBasic();                           // Use HTTP Basic Authentication

        http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery = "SELECT username, password, enabled FROM User WHERE username=?";
        String authQuery = "SELECT username, role FROM User WHERE username=?";
        auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(userQuery)
        .authoritiesByUsernameQuery(authQuery)
        .passwordEncoder(encoder);
    }
}
