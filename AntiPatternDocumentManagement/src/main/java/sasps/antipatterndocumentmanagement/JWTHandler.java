package sasps.antipatterndocumentmanagement;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

public class JWTHandler {
    Handler_GodClass.PersonRepository personRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(Handler_GodClass.PersonRepository personRepository, BCryptPasswordEncoder bcryptEncoder)
    {
        this.personRepository=personRepository;
        this.bcryptEncoder=bcryptEncoder;
    }

    public JWTHandler(BCryptPasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if(person == null){
            System.out.println("User not found with username: "+username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> person.getRole().name());
        return new User(person.getUsername(), person.getPassword(), authorities);
    }


    public Person save(Person person){
        Person newUser = new Person();
        newUser.setUsername(person.getUsername());
        newUser.setPassword(bcryptEncoder.encode(person.getPassword()));
        newUser.setRole(person.getRole());
        newUser.setSignature(null);
        personRepository.save(newUser);
        return newUser;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {


    }
}
