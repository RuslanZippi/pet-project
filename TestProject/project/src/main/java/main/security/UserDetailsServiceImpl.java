package main.security;

import main.model.User;
import main.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserDetailsServiceImpl(UserRep userRep) {
        this.userRep = userRep;
    }


    private final UserRep userRep;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRep.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user: " + email + " not found"));
        return SecurityUser.fromUser(user);
    }
}
