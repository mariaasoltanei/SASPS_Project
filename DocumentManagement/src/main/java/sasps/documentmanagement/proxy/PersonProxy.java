package sasps.documentmanagement.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.config.JwtConfig;
import sasps.documentmanagement.services.JwtUserDetailsService;
import sasps.documentmanagement.controllers.interfaces.PersonService;
import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.entities.Role;
import sasps.documentmanagement.exceptions.PersonNotFoundException;
import sasps.documentmanagement.repos.PersonRepository;
import sasps.documentmanagement.services.PersonServiceImpl;

import java.util.UUID;

@Service
public class PersonProxy implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonProxy.class);
    private final PersonService personService;
    private final JwtConfig jwtConfig;
    private final JwtUserDetailsService userDetailsService;
    private final PersonRepository personRepository;

    @Autowired
    public PersonProxy(PersonServiceImpl personService, JwtConfig jwtConfig, JwtUserDetailsService userDetailsService, PersonRepository personRepository)
    {
        this.personService = personService;
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
        this.personRepository = personRepository;
    }

    @Override
    public UUID createPerson(PersonDTO personDTO) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public PersonDTO findPersonByID(UUID id) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID deletePerson(UUID id) throws PersonNotFoundException {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID updatePerson(UUID id, PersonDTO personDTO) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    private boolean validateRole(UserDetails loggedInUser){
        return loggedInUser.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(Role.ROLE_USER.name()));
    }
    private boolean validateToken(String token, UserDetails loggedInUser){
        return jwtConfig.validateToken(token, loggedInUser);
    }

    private void verifyUser(String token) throws Exception {
        UserDetails loggedInUser = userDetailsService.loadUserByUsername(jwtConfig.extractUsername(token));
        if(!validateToken(token,loggedInUser)){
            LOGGER.warn("User is not authorized to create document");
            throw new Exception("INVALID_TOKEN");
        }
        if(!validateRole(loggedInUser)) {
            LOGGER.warn("User is not authorized to create document");
            throw new Exception("USER_NOT_AUTHORIZED");
        }

    }
    @Override
    public UUID proxyCreatePerson(PersonDTO personDTO) throws Exception {
        verifyUser(personDTO.getToken());
        UUID createdUserID = personService.createPerson(personDTO);
        LOGGER.info("User created with id "+createdUserID);
        return createdUserID;
    }

    //TODO: Implement all the other methods
    //TODO: Add relevant exception handling, not generic Exception
    @Override
    public PersonDTO proxyFindPersonByID(UUID id, String token) {
        return null;
    }

    @Override
    public UUID proxyDeletePerson(UUID id, String token) throws PersonNotFoundException {
        return null;
    }

    @Override
    public UUID proxyUpdatePerson(UUID id, PersonDTO personDTO) {
        return null;
    }
}
