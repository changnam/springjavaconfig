package com.honsoft.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.honsoft.web.domain.QuickGuideUser;
import com.honsoft.web.entity.Authority;
import com.honsoft.web.entity.User;
import com.honsoft.web.repository.AuthoritiesRepository;
import com.honsoft.web.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class QuickGuideUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException(username + "is not found.");
		}

		QuickGuideUser quickGuideUser = new QuickGuideUser();
		quickGuideUser.setUsername(user.getUsername());
		quickGuideUser.setPassword(user.getPassword());
		quickGuideUser.setAuthorities(getAuthorities(username));
		quickGuideUser.setEnabled(true);
		quickGuideUser.setAccountNonExpired(true);
		quickGuideUser.setAccountNonLocked(true);
		quickGuideUser.setCredentialsNonExpired(true);

		return quickGuideUser;
	}

	public Collection<GrantedAuthority> getAuthorities(String username) {
		List<Authority> authList = authoritiesRepository.findByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Authority authority : authList) {
			authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
		}
		return authorities;
	}

}
