package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev.dto.AuthResponseDTO;
import tn.esprit.pidev.dto.LoginDTO;
import tn.esprit.pidev.dto.RegisterDTO;
import tn.esprit.pidev.security.JWTGenerator;
import tn.esprit.pidev.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator; // HERE Inject the jwt Generator

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) { // HERE update the return type
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  loginDTO.getUsername(),
                  loginDTO.getPassword()
          )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication); // HERE Generate the token
        //String token = userDetailsService.login(loginDTO);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK); // HERE update the return
    }
    // ADMIN SIGNUP TODO (CHECK +EMPLACEMENT_PRINCIPAL) && FINISH
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        if(userDetailsService.isUsernameTaken(registerDTO.getUsername())) {
            return new ResponseEntity<>("Username is taken !", HttpStatus.BAD_REQUEST);
        }
        userDetailsService.register(registerDTO);
        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
    // TODO FORGET PASSWORD
    // TODO RESET PASSWORD
    // TODO LOGOUT (OR REMOVE)
}
