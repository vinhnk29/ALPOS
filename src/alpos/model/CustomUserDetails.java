package alpos.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import alpos.entity.Role;
import alpos.entity.User;


@SuppressWarnings("serial")
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
	private User user = null;

	public CustomUserDetails(User user) {
		super(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(int value) {
		return Role.toList(value).stream().map(role -> new SimpleGrantedAuthority(role.name()))
				.collect(Collectors.toList());
	}

}
 