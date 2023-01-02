package ebolasafe;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = false)
public class SecurityConfiguration {
	
	@Autowired
	static UserDetailsService userdetailsService;

	static class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest requesst, HttpServletResponse response,
                org.springframework.security.core.AuthenticationException authenticationException)
                throws IOException, ServletException {
            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Unauthorized: Authentication token was either missing or invalid.");
        }
    }
    

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {       
     	/*@Autowired
        private DataSource dataSource;
     	 @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth
            .jdbcAuthentication()
                .dataSource(dataSource)
               .usersByUsernameQuery(
                        "select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username,authority from authorities where username=?");
        }
        
        public void configure(AuthenticationManagerBuilder auth){
        	try {
				auth.userDetailsService(userdetailsService)
				.passwordEncoder(new BCryptPasswordEncoder());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
       */

    }

   @Configuration  
   @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	   
        @Override
        protected void configure(HttpSecurity http) throws Exception {
        	UnauthorizedEntryPoint uep = new UnauthorizedEntryPoint();
            http.authorizeRequests()
                .antMatchers("/rest/**","/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic().authenticationEntryPoint(uep);
         }

		@Override
		protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			// TODO Auto-generated method stub
			super.configure(auth);
			auth
            .inMemoryAuthentication()
                .withUser("admin").password("ebolasafe").roles("USER");
		}
        
    }
   
   /*@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	public static class ApiWebSecurityConfigurationAdapter extends	WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			UnauthorizedEntryPoint uep = new UnauthorizedEntryPoint();
			http.csrf().disable()
		            .antMatcher("/rest/**").authorizeRequests()
		            .antMatchers("/rest/**").authenticated()
	            .and()
	            	.httpBasic().authenticationEntryPoint(uep);   
		}
		
	}
	
	@Configuration
	@Order(2)
	public static class RegistrationWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().antMatcher("/register").authorizeRequests().antMatchers("/register").permitAll();
		}
	}*/
}