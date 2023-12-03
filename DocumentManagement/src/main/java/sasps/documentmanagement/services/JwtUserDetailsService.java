package sasps.documentmanagement.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.entities.Person;
import sasps.documentmanagement.repos.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    public JwtUserDetailsService(PersonRepository personRepository, BCryptPasswordEncoder bcryptEncoder)
    {
        this.personRepository=personRepository;
        this.bcryptEncoder=bcryptEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);
        if(person == null){
            throw new UsernameNotFoundException("User not found with username: "+username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> person.getRole().name());
        return new User(person.getUsername(), person.getPassword(), authorities);
    }


    public Person save(PersonDTO personDTO){
        Person newUser = new Person();
        newUser.setUsername(personDTO.getUsername());
        newUser.setPassword(bcryptEncoder.encode(personDTO.getPassword()));
        newUser.setRole(personDTO.getRole());
        newUser.setSignature(null);
        personRepository.save(newUser);
        return newUser;
    }
}
