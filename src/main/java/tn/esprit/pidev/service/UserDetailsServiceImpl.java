package tn.esprit.pidev.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.dto.AddUserDTO;
import tn.esprit.pidev.dto.GetUserDTO;
import tn.esprit.pidev.dto.LoginDTO;
import tn.esprit.pidev.dto.RegisterDTO;
import tn.esprit.pidev.model.Emplacement;
import tn.esprit.pidev.model.Role;
import tn.esprit.pidev.model.UserEntity;
import tn.esprit.pidev.repository.EmplacementRepository;
import tn.esprit.pidev.repository.RoleRepository;
import tn.esprit.pidev.repository.UserRepository;
import tn.esprit.pidev.security.JWTGenerator;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmplacementRepository emplacementRepository;
    // @Autowired
    // private AuthenticationManager authenticationManager; // TODO HERE
    @Autowired
    private JWTGenerator jwtGenerator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    public GetUserDTO getUser(final Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));
        if(user!=null) {
            GetUserDTO userDTO = new GetUserDTO();
            userDTO.setNom(user.getNom());
            userDTO.setPrenom(user.getPrenom());
            userDTO.setEmail(user.getEmail());
            userDTO.setUsername(user.getUsername());
            userDTO.setRoles(user.getRoles());
            userDTO.setEmplacement(user.getEmplacement());

            return userDTO;
        }
        return null;
    }
    public GetUserDTO addUser(AddUserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username is taken!");
        }
        UserEntity user = new UserEntity();
        user.setNom(userDTO.getNom());
        user.setPrenom(userDTO.getPrenom());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role role = roleRepository.findByName(userDTO.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singletonList(role));

        Emplacement emplacement = emplacementRepository.findById(userDTO.getEmplacement()).orElseThrow(() -> new RuntimeException("Emplacement not found"));
        user.setEmplacement(emplacement);

        user =  userRepository.save(user);

        GetUserDTO getUserDTO = new GetUserDTO();
        getUserDTO.setNom(user.getNom());
        getUserDTO.setPrenom(user.getPrenom());
        getUserDTO.setEmail(user.getEmail());
        getUserDTO.setRoles(user.getRoles());
        getUserDTO.setEmplacement(user.getEmplacement());

        return getUserDTO;
    }
    public GetUserDTO updateUser(UserEntity userEntity, final Long id) {
        userEntity.setId(id);
        userEntity = userRepository.save(userEntity);

        GetUserDTO userDTO = new GetUserDTO();
        userDTO.setNom(userEntity.getNom());
        userDTO.setPrenom(userEntity.getPrenom());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setRoles(userEntity.getRoles());
        userDTO.setEmplacement(userEntity.getEmplacement());

        return userDTO;
    }
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
    public void deleteUser(final Long id) {
        userRepository.deleteById(id);
    }
    public boolean isUsernameTaken(String username) {
        return userRepository.existsByUsername(username);
    }
    public void register(RegisterDTO registerDTO) {
        /*if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ResponseEntity<>("Username is taken !", HttpStatus.BAD_REQUEST);
        }*/
        UserEntity user = new UserEntity();
        user.setNom(registerDTO.getNom());
        user.setPrenom(registerDTO.getPrenom());
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Role roles = roleRepository.findByName("ADMIN").orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);
        // return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
    // TODO FIX CIRCULAR DEPENDENCY ERROR
    /* public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtGenerator.generateToken(authentication);
    }*/
}
