//package com.corsojava.album.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import com.corsojava.album.security.DatabaseUserDetailService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//	 @Bean
//	  SecurityFilterChain filterChain(HttpSecurity http) 
//	  	throws Exception {
//	    http.authorizeHttpRequests()
//	        .requestMatchers("/categories", "/categories/create").hasAuthority("admin") 	//per creare o modificare un libro bisogna essere ADMIN
//	        .requestMatchers(HttpMethod.POST, "/foto/**").hasAuthority("admin")		//per fare il POST su /pizza (richiesto per eliminare un libro) bisogna essere ADMIN//per accedere alle categorie bisogna essere ADMIN
//	        .requestMatchers("/foto","/foto/**").hasAnyAuthority("admin") 	//per accedere all'elenco libri (/books) o dettaglio libri (/books/**) bisogna esser USER o ADMIN
//	        .requestMatchers("/**").permitAll()											//chiunque può accedere alla Home
//	        .and().formLogin()			//abilita il supporto al form login (auto generato)
//	        .and().logout()			//abilita il supporto al form logout (auto generato)
//	    	.and().exceptionHandling()
//	    	.accessDeniedPage("/access-denied.html"); //pagina personalizzata in caso di accesso negato
//	    
//	    return http.build();
//	  }
//	  
//	  @Bean
//	  DatabaseUserDetailService userDetailsService() {
//	    return new DatabaseUserDetailService();
//	  }
//	  
//	  @Bean
//	  PasswordEncoder passwordEncoder() {
//	    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	  }  
//	  
//	  @Bean
//	  DaoAuthenticationProvider authenticationProvider() {
//	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	 
//	    authProvider.setUserDetailsService(userDetailsService());
//	    authProvider.setPasswordEncoder(passwordEncoder());
//	    	
//	    
//	  //  necessario per salvare le password nel database in modo cryptato
//	    System.out.println(passwordEncoder().encode("pippo"));
//	    
//	    return authProvider;
//	  }  
//}
