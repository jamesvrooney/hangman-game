package com.rooney.james.service;

import com.rooney.james.model.Player;
import com.rooney.james.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jamesvrooney on 30/03/17.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private PlayerRepository playerRepository;

    public UserDetailsServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        /*Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : player.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }*/

        return new org.springframework.security.core.userdetails.User(player.getUsername(), player.getPassword(), grantedAuthorities);
//        return new User(player.getUsername(), player.getPassword(), null);
    }
}
