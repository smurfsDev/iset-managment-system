package com.projetIntegraion.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.User;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.UserRole;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.UserRoleRepository;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String AUTHORITIES_CLAIM_NAME = "roles";
	@Autowired
	UserService userDetailsService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;
	// @Autowired
	// private AccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
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
		http.exceptionHandling().accessDeniedPage("/accessDenied");

		http.csrf().disable()
				.authenticationProvider(getProvider())
				.formLogin().loginPage("/login")
				.loginProcessingUrl("/login")
				.successHandler(new AuthentificationLoginSuccessHandler())
				.failureHandler(new AuthentificationLoginErrorHandler())
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
				.invalidateHttpSession(true)
				.and()
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/logout").permitAll()
				.antMatchers("/accept", "/decline", "/categorieMateriel", "/listeChefDepartments", "/acceptCD",
						"/declineCD")
				.hasAnyRole("ADMIN")
				.antMatchers("/classe", "/deleteClass", "/showCreateClass", "/createClass", "/modifierClass",
						"/updateClass")
				.hasAnyRole("CHEFDEPARTEMENT")
				.antMatchers("/showCreateDcc", "/deleteDcc",
						"/modifierDcc", "/blogClub")
				.hasAnyRole("STUDENT")
				.antMatchers(
						"/blogClub", "/showCreateBlog", "/createAbout", "/deleteAbout",
						"/modifierAbout",
						"/showCreateActivity", "/createActivity", "/deleteActivities", "/modifierActivities",
						"/showManageBlog",
						"/showCreateBoard", "/createBoard", "/deleteBoard", "/showCreateProject", "/createProject",
						"/deleteProject", "/modifierProject",
						"/materiel", "/showMateriel",
						"/listeDS", "/ShowcreateDS", "/showEditDS", "/updateDS",
						"/modifierDS", "/listeDm", "/showCreateDm", "/showEditDm",
						"/updateDmm", "/setQuantite", "/deleteMaterielDemande",
						"/deleteDmm",

						"/deleteDS")
				.hasAnyRole("RESPONSABLE")
				.antMatchers("/showListCategories", "/showCreateCategorieForm", "/saveCategorieMateriel",
						"/showEditCategorieForm", "/updateCategorieMateriel", "/deleteCategorieMateriel",
						"/showListMateriel", "/showCreateMaterielForm", "/saveMateriel", "/showEditMaterielForm",
						"/updateMateriel", "/deleteMateriel")
				.hasAnyRole("ADMIN", "CHEFDEPARTEMENT")
				.antMatchers("/listeDcc")
				.hasAnyRole("RESPONSABLE", "STUDENT", "ADMIN")
				.antMatchers("/login").permitAll()
				.antMatchers("/register").permitAll()
				.antMatchers("/webjars/**").permitAll()
				.anyRequest().authenticated();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private class AuthentificationLoginSuccessHandler extends
			SimpleUrlAuthenticationSuccessHandler {
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {

			String username = request.getParameter("username");
			User user = userRepository.findUserWithName(username).get();
			UserRole ur = userRoleRepository.findFirstByUserId(user.getId()).isPresent()
					? userRoleRepository.findFirstByUserId(user.getId()).get()
					: null;
			response.sendRedirect(ur != null ? ur.getStatus() != 1 ? "/login?error=2" : "/listeDcc" : "/listeDcc");
			response.setStatus(
					ur != null ? ur.getStatus() != 1 ? HttpServletResponse.SC_UNAUTHORIZED : HttpServletResponse.SC_OK
							: HttpServletResponse.SC_OK);
		}

	}

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.AuthenticationException exception)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.sendRedirect("/login?error=1");
	}

	private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

		@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			// TODO Auto-generated method stub
			super.onLogoutSuccess(request, response, authentication);
		}

	}

	private class AuthentificationLoginErrorHandler extends
			SimpleUrlAuthenticationFailureHandler {
		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				org.springframework.security.core.AuthenticationException exception)
				throws IOException, ServletException {

			response.sendRedirect("/login?error=1");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		}

	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandlerImpl();
	}

}