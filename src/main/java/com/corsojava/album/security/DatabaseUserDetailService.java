package com.corsojava.album.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.corsojava.album.model.User;
import com.corsojava.album.repository.UserRepository;
import com.corsojava.album.security.DatabaseUserDetails;

@Service
public class DatabaseUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(String username) 
    	throws UsernameNotFoundException {
      Optional<User> user = userRepository.findByUsername(username);
      if(user.isPresent()) {
        return new DatabaseUserDetails(user.get());
      } else throw new UsernameNotFoundException("Username not found");
    }
}
