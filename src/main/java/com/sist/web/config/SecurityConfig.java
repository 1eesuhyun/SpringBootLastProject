package com.sist.web.config;
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.sist.web.security.LoginFailHandler;
import com.sist.web.security.LoginSuccessHandler;
import lombok.RequiredArgsConstructor;

/*
 *  보안 설정
 *  
 *  ·요청 -> Security FilterChain -> 인증 -> 인가 -> Controller -> View
 *                 |                |     | 권한 체크              | 
 *           설정(인증 / 인가)   로그인된 사용자 구별               접근 권한 => 메뉴를 제한
 *           
 *  ·인증
 *   - 로그인 / 아이디, 비밀번호
 *      |
 *     성공 => Authentication 객체 생성 => 주요 정보를 저장
 *                 | 인가
 *                   hasRole(), authenticated()
 *                   permitAll()
 *     실패
 *     
 *     Client - Securiry Filter - Authentication - Authorization - Controller 
 *                                    |
 *                                Session / Cookie
 *                                 |          |
 *                               일반 보안     JWT
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final LoginSuccessHandler shandler;
	private final LoginFailHandler fhandler;
	private final javax.sql.DataSource dataSource;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)
	throws Exception
	{
		http
		.csrf(scrf -> scrf.disable())
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/","/member/**").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
		)
		.formLogin(form -> form
				.loginPage("/member/login")
				.loginProcessingUrl("/member/login_process")
				.usernameParameter("userid")
				.passwordParameter("userpwd")
				.defaultSuccessUrl("/main",false)
				.successHandler(shandler)
				.failureHandler(fhandler) 
				.permitAll()
		)
		.rememberMe(remember -> remember
				.key("my-security-key")
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(60*60*24)
				//.userDetailsService(userDetailService)
		)
		.logout(logout -> logout
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/main")
				.invalidateHttpSession(true)
				.deleteCookies("remember-me","JESSIONID")
		);
		return http.build();
	}
	// 인증 관리자
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http,BCryptPasswordEncoder passwordEncoder) 
	throws Exception
	{
		AuthenticationManagerBuilder builder=http.getSharedObject(AuthenticationManagerBuilder.class);
		builder
			.userDetailsService(jdbcUserDetailsService())
			.passwordEncoder(passwordEncoder);
		return builder.build();
	}
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsService()
	{
		JdbcUserDetailsManager manager=new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("SELECT userid as username,userpwd as password,enabled "
				+ "FROM project_member_1 WHERE userid=?");
		manager.setAuthoritiesByUsernameQuery("SELECT userid as username,authority "
				+ "FROM authority_1 WHERE userid=?");
		return manager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}