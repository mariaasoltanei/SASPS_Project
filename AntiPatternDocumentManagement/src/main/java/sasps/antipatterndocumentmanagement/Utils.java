package sasps.antipatterndocumentmanagement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.*;
import java.util.function.Function;


// Numai eu si Dumnezeu stim ce face clasa asta
// Un nume mai bun ar fi fost "Handler" sau "Service"
// Updateaza aici acest counter daca te-ai dat batut din a incerca sa intelegi ce face clasa asta
// Counter: 1
@Service
public class Utils implements UserDetailsService {
    private final BCryptPasswordEncoder bcryptEncoder;
    Poltergeist poltergeist;

    public Utils(BCryptPasswordEncoder bcryptEncoder, Poltergeist poltergeist) {
        this.bcryptEncoder = bcryptEncoder;
        this.poltergeist = poltergeist;
    }

    ////////////////////////////////
    /////// JWT classes ////////////
    ////////////////////////////////
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JwtRequest {
        private String username;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    public static class JwtResponse {
        private final String token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = poltergeist.personRepository.findByUsername(username);
        if (person == null) {
            System.out.println("User not found with username: " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> person.getRole().name());
        return new User(person.getUsername(), person.getPassword(), authorities);
    }


    @RestController
    @CrossOrigin
    public static class JwtAuthenticationController {

        private final AuthenticationManager authenticationManager;

        private final ConfigUtils.JwtConfig jwtTokenUtil;

        private final JwtUserDetailsService userDetailsService;

        @Autowired
        public JwtAuthenticationController(AuthenticationManager authenticationManager, ConfigUtils.JwtConfig jwtTokenUtil, JwtUserDetailsService userDetailsService) {
            this.authenticationManager = authenticationManager;
            this.jwtTokenUtil = jwtTokenUtil;
            this.userDetailsService = userDetailsService;
        }

        @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
        public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        }

        private void authenticate(String username, String password) throws Exception {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            } catch (DisabledException ex) {
                throw new Exception("USER_DISABLED", ex);
            } catch (BadCredentialsException ex) {
                throw new Exception("INVALID_CREDENTIALS", ex);
            }
        }

        @RequestMapping(value = "/register", method = RequestMethod.POST)
        public ResponseEntity<?> saveUser(@RequestBody Person person) {
            return ResponseEntity.ok(userDetailsService.save(person));
        }

        @Service
        public static class JwtUserDetailsService implements UserDetailsService {
            private final Poltergeist poltergeist;
            private final BCryptPasswordEncoder bcryptEncoder;

            public JwtUserDetailsService(Poltergeist poltergeist, BCryptPasswordEncoder bcryptEncoder) {
                this.poltergeist = poltergeist;
                this.bcryptEncoder = bcryptEncoder;
            }

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Person person = poltergeist.repositories.findByUsername(username);
                if (person == null) {
                    throw new UsernameNotFoundException("User not found with username: " + username);
                }
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add((GrantedAuthority) () -> person.getRole().name());
                return new User(person.getUsername(), person.getPassword(), authorities);
            }


            public Person save(Person person) {
                Person newUser = new Person();
                newUser.setUsername(person.getUsername());
                newUser.setPassword(bcryptEncoder.encode(person.getPassword()));
                newUser.setRole(person.getRole());
                newUser.setSignature(null);
                personRepository.save(newUser);
                return newUser;
            }
        }
    }
}
