package poly.store;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomerOAuth2User implements OAuth2User {

	private OAuth2User oauth2User;
	
	public CustomerOAuth2User(OAuth2User aouth2User) {
		this.oauth2User = aouth2User;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oauth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oauth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oauth2User.getAttribute("name");
	}
	
	public String getEmail() {
		return oauth2User.getAttribute("email");
	}
	
	public String getUsername() {
		String email = oauth2User.getAttribute("email");
		return email.substring(0, email.indexOf("@"));
	}
	
	public String getImage() {
		return oauth2User.getAttribute("picture");
	}
	
}
