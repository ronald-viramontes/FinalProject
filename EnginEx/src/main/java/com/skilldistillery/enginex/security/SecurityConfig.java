package com.skilldistillery.enginex.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    public void configure(WebSecurity web) throws Exception {
    	web
    		.ignoring()
    			.antMatchers("/public/**");
    	web
    		.ignoring()
    			.antMatchers(HttpMethod.GET, "/api/posts");
    	web
			.ignoring()
	    		.antMatchers(HttpMethod.GET, "/api/stats");
    	web
    		.ignoring()
    			.antMatchers(HttpMethod.GET, "/api/types");
    	
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
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
			
    	    	 
    	http
    		.csrf().disable()
    		.authorizeRequests()
    			
    		.antMatchers(HttpMethod.OPTIONS, "/api/**", "/**")
		        	.permitAll() 
		        	
		        .antMatchers(HttpMethod.OPTIONS, "/**")
		        	.permitAll()       	
		       	
		        .antMatchers(HttpMethod.GET, "/api/jobs", "/api/jobs/**", "/api/jobstatus/**", 
		        		"/api/comments/**", "/api/jobtypes/**", "/api/jobdetails/**",
		        		"/api/skills/**", "/api/apps/**", "/api/users", "/api/educations/**", "/api/users/skills/**",
		        		"/api/userapps", "/api/userjobs/**", "/api/userapps/**", "/api/userapps/app/**", 
		        		"/api/appstats", "/api/appstats/**")
		        	.permitAll()
		        	
		        	
		        .antMatchers(HttpMethod.POST, "/api/jobs",  "/api/jobs/**", "/api/jobstatus/**", 
	        			"/api/comments/**", "/api/jobtypes/**", "/api/jobdetails/**",
	        			"/api/skills/**", "/api/apps/**", "/api/users", "/api/educations/**", 
	        			"/api/userapps", "/api/userjobs", "/api/userjobs/**", "/api/userapps/**", "/api/userapps/new/**", 
	        			"/api/userapps/new", "/api/userapps/new/")
		        	.permitAll()
		        			        	   	
		        
	        	.antMatchers(HttpMethod.PUT, "/api/jobs",  "/api/jobs/**", "/api/jobstatus/**", 
	        			"/api/comments/**", "/api/jobtypes/**", "/api/jobdetails/**",
	        			"/api/skills/**", "/api/apps/**", "/api/users", "/api/users/**", "/api/educations/**", 
	        			"/api/userapps", "/api/userjobs/**", "/api/userapps/**",
	        			"/api/admin/eord/**/", "/api/admin/**")
	        		.permitAll() 

	        	.antMatchers(HttpMethod.DELETE, "/api/jobs",  "/api/jobs/**", "/api/jobstatus/**", 
	        			"/api/comments/**", "/api/jobtypes/**", "/api/jobdetails/**",
	        			"/api/skills/**", "/api/apps/**", "/api/users", "/api/educations/**", 
	        			"/api/userapps", "/api/userjobs/**/", "/api/userapps/**", "/api/admin/delete/**")
	        		.permitAll() 

	        	    .antMatchers("/api/**").authenticated() 
	                .anyRequest().permitAll()           
	                .and()
	                .httpBasic();                          
    	    	
	                http
		                .sessionManagement()
		                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		                .and()
//		                .logout()
//	                	.logoutUrl("http://18.189.87.44:8080/EnginEx/")
//	                	.deleteCookies("JSESSIONID");
	                  
    }
    
}
