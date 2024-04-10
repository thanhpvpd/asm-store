package poly.store;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import poly.store.entity.Account;
import poly.store.service.AccountService;

@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	AccountService accountService;
	
	BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> user = accountService.findByUsername(username);
		if (user.isPresent()) {
			Account userObj = user.get();
			return User.builder()
					.username(userObj.getUsername())
					.password(pe.encode(userObj.getPassword()))
					//Lấy mảng các vai trò
					.roles(userObj.getAuthorities().stream()
							.map(er -> er.getRole().getId())
							.collect(Collectors.toList()).toArray(new String[0])
						)
					.build();
		} else {
			throw new UsernameNotFoundException("Không tìm thấy " + username);
		}
	}
	
	

}
