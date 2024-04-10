package poly.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	MyUserDetailService myUserDetailService;
	@Autowired
	CustomerOAuth2UserService oaAuth2UserService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			//Vô hiệu hóa CSRF
			.csrf(c -> c.disable())
			.cors(c -> c.disable())
			.authorizeHttpRequests(registry ->{
				registry.requestMatchers("/order/**").authenticated();
				registry.requestMatchers("/admin/**").hasAnyRole("STAF", "DIRE");
				registry.requestMatchers("/rest/authorities").hasRole("DIRE");
				registry.anyRequest().permitAll();
			});
		
		httpSecurity.formLogin(loginPage ->{
			//Địa chỉ form đăng nhập
			loginPage.loginPage("/login/sign-in").permitAll();
			loginPage.loginProcessingUrl("/login/security");
			loginPage.defaultSuccessUrl("/login/security/success", false);
			loginPage.failureUrl("/login/security/error");
			
			
			
		});
		
		httpSecurity.oauth2Login(oauth2 ->{
			oauth2.loginPage("/login/oauth2/authorization/google");
			oauth2.userInfoEndpoint(u ->{
				u.userService(oaAuth2UserService);
			});
			oauth2.defaultSuccessUrl("/login/security/success", false);
			oauth2.failureUrl("/login/security/error");
		});
	
		httpSecurity.rememberMe(re ->{
			// hiệu lực trong 24h
			re.userDetailsService(userDetailsService());
			re.tokenValiditySeconds(86400);
		});
		
		
		httpSecurity.logout(lo ->{
			lo.logoutUrl("/login/security/out");
			lo.logoutSuccessUrl("/login/security/out/success");
		});
		
		return httpSecurity.build();
	}
	
	// tạo user bằng tay
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails userDetails = User.builder()
//				.username("user10")
//				.password("$2a$12$PBVDmwJ1loQ4x7j2EEC7/.kU/h/c0F9ogl3Vur0wGKBM1zD4TyUu6")
//				.roles("STAF")
//				.build();
//		return new InMemoryUserDetailsManager(userDetails);
//	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return myUserDetailService;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		/*truy vấn thông tin người dùng 
		 * từ một nguồn dữ liệu như cơ sở dữ liệu hoặc 
		 * bất kỳ nguồn dữ liệu nào khác.
		 */
		provider.setUserDetailsService(userDetailsService());
		//được sử dụng để mã hóa mật khẩu 
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	/* Các request này sẽ không được xử lý bởi Spring Security 
	 * cho phép chạy khi chưa đăng nhập
	 */
	@Bean
	WebSecurityCustomizer customizer() {
		return web->{
			web.ignoring().requestMatchers("/static/**");
		};
	}
	
	
}
