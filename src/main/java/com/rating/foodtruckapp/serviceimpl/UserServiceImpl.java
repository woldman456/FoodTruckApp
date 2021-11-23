package com.rating.foodtruckapp.serviceimpl;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import com.rating.foodtruckapp.controller.dto.UserRegistrationDto;
import com.rating.foodtruckapp.model.Role;
import com.rating.foodtruckapp.model.User;
import com.rating.foodtruckapp.repository.UserRepository;
import com.rating.foodtruckapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     * this method provides the service layer logic for saving a user and user details
     * @param registration
     * @return
     */
    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(List.of(new Role("ROLE_USER")));
        user.setActive(true);
        return userRepository.save(user);
    }

    /**
     * this method provides the logic for loading a user by username (email address)
     * @param email
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("finding email address");
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /**
     * this method provides the logic for creating a collection of roles to be used to apply to existing users
     * @param roles
     * @return
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }


}