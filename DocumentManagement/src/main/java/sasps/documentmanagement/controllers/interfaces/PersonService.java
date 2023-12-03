package sasps.documentmanagement.controllers.interfaces;

import org.springframework.stereotype.Service;
import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.exceptions.PersonNotFoundException;

import java.util.UUID;

@Service
public interface PersonService {
    UUID createPerson(PersonDTO personDTO);
    PersonDTO findPersonByID(UUID id);
    UUID deletePerson(UUID id) throws PersonNotFoundException;
    UUID updatePerson(UUID id, PersonDTO personDTO);

    UUID proxyCreatePerson(PersonDTO personDTO) throws Exception;
    PersonDTO proxyFindPersonByID(UUID id, String token);
    UUID proxyDeletePerson(UUID id, String token) throws PersonNotFoundException;
    UUID proxyUpdatePerson(UUID id, PersonDTO personDTO);
}
