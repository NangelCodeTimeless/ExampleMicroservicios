package com.example.store.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.store.customer.bean.Users;
import com.example.store.customer.repository.UsersRepository;

@Service
public class InBdUserDetailsService implements UserDetailsService {

	@Autowired
	UsersRepository repository;

	/**
	 * Piense en una GrantedAuthority como un "permiso" o un "derecho". Esos
	 * "permisos" se expresan (normalmente) como cadenas (con el método
	 * getAuthority()). Esas cadenas le permiten identificar los permisos y dejar
	 * que sus votantes decidan si conceden acceso a algo. Puede otorgar diferentes
	 * GrantedAuthoritys (permisos) a los usuarios colocándolos en el contexto de
	 * seguridad. Normalmente lo hace implementando su propio UserDetailsService que
	 * devuelve una implementación UserDetails que devuelve las Autoridades Granted
	 * necesarias.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = repository.findByUsernameAndType(username, "customer");
		List<GrantedAuthority> roles = new ArrayList<>();// para obtener una authority para autorizar/controlar un acceso.
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		UserDetails userDetails = new User(users.getUsername(), users.getPassword(), roles);
		return userDetails;
	}
}
