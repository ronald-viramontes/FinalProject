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
import org.springframework.web.cors.CorsConfiguration;

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
    	.passwordEncoder(encoder)
    	.rolePrefix("ROLE_");
    }

    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	CorsConfiguration configCors = new CorsConfiguration();
    	configCors.applyPermitDefaultValues();
//    	configCors.setAllowCredentials(true);
    	
    	
    		
    	http
    	 
        
    	.csrf().disable()
    		.authorizeRequests()
    			.antMatchers(HttpMethod.OPTIONS, "/api/**", "/**")
		        	.permitAll() 
		        	
		        .antMatchers(HttpMethod.OPTIONS, "/**")
		        	.permitAll()
		        .antMatchers(HttpMethod.GET, "/admin")
		        	.hasRole("ADMIN")
		        	.antMatchers(HttpMethod.POST, "/admin")
		        	.hasRole("ADMIN")
		        	.antMatchers(HttpMethod.PUT, "/admin")
		        	.hasRole("ADMIN")
		        	.antMatchers(HttpMethod.DELETE, "/admin")
		        	.hasRole("ADMIN")
		       	
		        .antMatchers(HttpMethod.GET, "/api/jobs", "/api/jobs/**", "/api/jobstatus/**", 
		        		"/api/comments/**", "/api/jobtypes/**", "/api/jobdetails/**",
		        		"/api/skills/**", "/api/apps/**", "/api/users", "/api/educations/**", 
		        		"/api/userapps", "/api/userjobs/**", "/api/userapps/**")
		        	.permitAll()
		        	
		        	
		        .antMatchers(HttpMethod.POST, "/api/jobs",  "/api/jobs/**", "/api/jobstatus/**", 
	        			"/api/comments/**", "/api/jobtypes/**", "/api/jobdetails/**",
	        			"/api/skills/**", "/api/apps/**", "/api/users", "/api/educations/**", 
	        			"/api/userapps", "/api/userjobs/**", "/api/userapps/**", "/api/userapps/new/**", 
	        			"/api/userapps/new", "/api/userapps/new/")
		        	.permitAll()
		        			        	   	
	        	.antMatchers(HttpMethod.PUT, "/api/jobs",  "/api/jobs/**", "/api/jobstatus/**", 
	        			"/api/comments/**", "/api/jobtypes/**", "/api/jobdetails/**",
	        			"/api/skills/**", "/api/apps/**", "/api/users", "/api/educations/**", 
	        			"/api/userapps", "/api/userjobs/**", "/api/userapps/**")
	        		.permitAll() 
	        		
	        	.antMatchers(HttpMethod.DELETE, "/api/jobs",  "/api/jobs/**", "/api/jobstatus/**", 
	        			"/api/comments/**", "/api/jobtypes/**", "/api/jobdetails/**",
	        			"/api/skills/**", "/api/apps/**", "/api/users", "/api/educations/**", 
	        			"/api/userapps", "/api/userjobs/**", "/api/userapps/**")
	        		.permitAll() 

	        	    .antMatchers("/api/**").authenticated() 
	                .anyRequest().permitAll()           
	                .and()
	                .httpBasic();                          
    	    	
	                http
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	                
	             
	              
	              
	                
		        
//		        .antMatchers(HttpMethod.GET, "/api/jobstatus/**")
//		        	.permitAll() 
//		        .antMatchers(HttpMethod.GET, "/api/comments/**")
//		        	.permitAll()        
//		        .antMatchers(HttpMethod.GET, "/api/jobtypes/**")
//		        	.permitAll()
//		        .antMatchers(HttpMethod.GET, "/api/jobdetails/**")
//		        	.permitAll()
//		        .antMatchers(HttpMethod.GET, "/api/skills/**")
//		        	.permitAll()
//		        .antMatchers(HttpMethod.GET, "/api/apps/**")
//		        	.permitAll()
//		        .antMatchers(HttpMethod.GET, "/api/users")
//		        	.permitAll()
//		        .antMatchers(HttpMethod.GET, "/api/users/**")
//		        	.permitAll() 
//		        .antMatchers(HttpMethod.GET, "/api/educations/**")
//		        	.permitAll() 
//		        .antMatchers(HttpMethod.GET, "/api/userapps")
//		        	.permitAll()
//		        .antMatchers(HttpMethod.GET, "/api/userjobs/**")
//		        	.permitAll() 
//        
//        
//        .antMatchers(HttpMethod.GET, "/api/userapps/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.POST, "/api/userapps/new/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.POST, "/api/userapps/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.PUT, "/api/userapps/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.DELETE, "/api/userapps/**").permitAll() // Requests for our REST API must be authorized.
//
//        .antMatchers(HttpMethod.POST, "/api/userjobs/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.PUT, "/api/userjobs/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.DELETE, "/api/userjobs/**").permitAll() // Requests for our REST API must be authorized.
//
//        .antMatchers(HttpMethod.POST, "/api/jobs").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.PUT, "/api/jobs/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.DELETE, "/api/jobs/**").permitAll() // Requests for our REST API must be authorized.
//        
//        .antMatchers(HttpMethod.POST, "/api/apps/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.PUT, "/api/users/**").permitAll() // Requests for our REST API must be authorized.
//        .antMatchers(HttpMethod.DELETE, "/api/users/**").permitAll() // Requests for our REST API must be authorized.
//        
//        .antMatchers(HttpMethod.POST, "/api/comments/**").permitAll() // Requests for our REST API must be authorized.
//        
//        
//        
//        .antMatchers("/api/**").authenticated() 
//        .anyRequest().permitAll()           
//        .and()
//        .httpBasic();                          
//
//        http
//        .sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
   
        
    }

   


  
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
//      HttpServletResponse response = (HttpServletResponse) res;
//      HttpServletRequest request = (HttpServletRequest) req;
//      System.out.println("WebConfig; "+request.getRequestURI());
//      response.setHeader("Access-Control-Allow-Origin", "*");
//      response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//      response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
//      response.setHeader("Access-Control-Max-Age", "3600");
//      response.setHeader("Access-Control-Allow-Credentials", "true");
//      response.setHeader("Access-Control-Expose-Headers", "Authorization");
//      response.addHeader("Access-Control-Expose-Headers", "USERID");
//      response.addHeader("Access-Control-Expose-Headers", "ROLE");
//      response.addHeader("Access-Control-Expose-Headers", "responseType");
//      response.addHeader("Access-Control-Expose-Headers", "observe");
//      System.out.println("Request Method: "+request.getMethod());
//      if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
//          try {
//              chain.doFilter(req, res);
//          } catch(Exception e) {
//              e.printStackTrace();
//          }
//      } else {
//          System.out.println("Pre-flight");
//          response.setHeader("Access-Control-Allow-Origin", "*");
//          response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
//          response.setHeader("Access-Control-Max-Age", "3600");
//          response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +
//          "USERID"+"ROLE"+
//                  "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
//          response.setStatus(HttpServletResponse.SC_OK);
//      }
//
//    }
    
}
