package com.sundirect.crm.config;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.sundirect.crm.bean.AppUser;
import com.sundirect.crm.smsentity.Login;
import com.sundirect.crm.smsentity.Role;
import com.sundirect.crm.smsentity.Tenant;
import com.sundirect.crm.smsrepo.TenantRepository;
import com.sundirect.crm.smsrepo.UserRepository;

import antlr.StringUtils;

@Component
public class AppUserServiceImpl implements AppUserService {

	private static final Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);

	@Autowired
	UserRepository userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	TenantRepository tenantRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Login user = userRepo.findByUsername(username);
		log.info("user" + user.getTenantName() + "::" + user.getUsername() + "::" + user.getRoles().toString());
		Set<GrantedAuthority> grantAuth = new HashSet<>();
		user.getRoles().forEach(role -> grantAuth.add(new SimpleGrantedAuthority(role.getAuthority())));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantAuth);
	}

	@Override
	public void save(AppUser appUser) {
		Login user = new Login();

		user.setUsername(appUser.getUsername());
		user.setPassword(passwordEncoder.encode(appUser.getPassword()));		
		user.setTenantName(appUser.getTenantName());
		log.info("appuser tenant name" + appUser.getTenantId());

		Optional<Tenant> tent = tenantRepository.findByTenantName(appUser.getTenantName());
		if (tent.isPresent()) {
			Tenant optent = tent.get();
			log.info("optnt in appuserservice" + optent.getId());
			
			user.setTenantId(optent.getTenantId());
			log.info("userid" + user.getTenantId());
			user.setRoles(appUser.getRoles().stream().map(temp -> new Role(temp, user)).collect(Collectors.toSet()));
			userRepo.save(user);
		}
	}

	@Override
	public List<AppUser> fetchUsers() {
		List<AppUser> users = new ArrayList<>();
		userRepo.findAll().forEach(u -> users.add(getModelFromEntity(u)));
		return users;
	}

	private AppUser getModelFromEntity(Login u) {
		AppUser appUser = new AppUser();
		appUser.setUsername(u.getUsername());
		appUser.setPassword(u.getPassword());
		appUser.setTenantId(u.getTenantId());
		appUser.setId(String.valueOf(u.getId()));
		appUser.setRoles(u.getRoles().stream().map(Role::getAuthority).collect(Collectors.toList()));
		return appUser;
	}

	@Override
	public AppUser findUsername(String username) {
		Login user = userRepo.findByUsername(username);
		//if (Optional.ofNullable(user).isPresent())
		if(null != user)	
			return getModelFromEntity(user);
		return null;
	}

	@Override
	public AppUser getCurrentUser() {
		AppUser appUser = null;
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		if (auth != null) {
			String userName = auth.getName();
			appUser = this.findUsername(userName);
		}
		return appUser;
	}

}
