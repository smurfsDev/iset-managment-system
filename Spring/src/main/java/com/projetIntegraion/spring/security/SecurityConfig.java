package com.projetIntegraion.spring.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;



@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String AUTHORITIES_CLAIM_NAME = "roles";
	@Autowired
	UserService userDetailsService;
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 // TODO Auto-generated method stub
	/* PasswordEncoder passwordEncoder = passwordEncoder ();
	 auth.inMemoryAuthentication().withUser("admin")
	 .password(passwordEncoder.encode("123")).roles("ADMIN");
	 auth.inMemoryAuthentication().withUser("Najla")
	 .password(passwordEncoder.encode("123")).roles("AGENT","USER");
	 auth.inMemoryAuthentication().withUser("user1")
	 .password(passwordEncoder.encode("123")).roles("USER");*/
		//auth.userDetailsService(userDetailsService);
	 } 
	@Bean
	public AuthenticationProvider getProvider() {
	 AppAuthProvider provider = new AppAuthProvider();
	provider.setUserDetailsService(userDetailsService);
	return provider;
	 }
    

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
		.authenticationProvider(getProvider())
		.formLogin()
		.loginProcessingUrl("/login")
		.successHandler(new AuthentificationLoginSuccessHandler())
		.failureHandler(new SimpleUrlAuthenticationFailureHandler())
		.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
		.invalidateHttpSession(true)
		.and()
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/logout").permitAll();
		//.anyRequest().authenticated(); 
		

		http.authorizeRequests().antMatchers("/listeDcc","/accept","/decline","/categorieMateriel")
		.hasAnyAuthority("ADMIN");
		 http.authorizeRequests().antMatchers("/listeDcc","/showCreateDcc", "/deleteDcc",
		 "/modifierDcc","/blogClub")
		 .hasAnyAuthority("STUDENT");
		 http.authorizeRequests().antMatchers("/listeDcc","/showCreateDcc", "/deleteDcc",
		 "/modifierDcc","/blogClub","/showCreateBlog","/createAbout","/deleteAbout","/modifierAbout",
		 "/showCreateActivity","/createActivity","/deleteActivities","/modifierActivities","/showManageBlog","/showCreateBoard"
		 ,"/createBoard","/deleteBoard","/showCreateProject","/createProject","/deleteProject","/modifierProject",
		 "/listeDm","/showCreateDm","/showEditDm","/updateDmm","/deleteDmm","/materiel","/showMateriel",
		 "/deleteMateriel","/setQuantite","/listeDS","/ShowcreateDS","/showEditDS","/updateDS","/modifierDS",
		 "/deleteDS")
		 .hasAnyAuthority("RESPONSABLE");
		 http.authorizeRequests()
		 .antMatchers("/supprimerProduit","/modifierProduit","/updateProduit")
		 .hasAuthority("ADMIN");
		 http.authorizeRequests().antMatchers("/login").permitAll();
		 http.authorizeRequests().antMatchers("/register").permitAll();
		 http.authorizeRequests().antMatchers("/webjars/**").permitAll();
		 http.authorizeRequests().anyRequest().authenticated();
		 http.formLogin().loginPage("/login"); 
		 
		 http.exceptionHandling().accessDeniedPage("/accessDenied");
		 

	}
	@Bean
	 public PasswordEncoder passwordEncoder () {
	 return new BCryptPasswordEncoder();
	 }
	private class AuthentificationLoginSuccessHandler extends
	SimpleUrlAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	Authentication authentication) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
	 } 

	}
	private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

		@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			// TODO Auto-generated method stub
			super.onLogoutSuccess(request, response, authentication);
		}
		
		 }
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	 return new AccessDeniedHandlerImpl();
	 }
	



}