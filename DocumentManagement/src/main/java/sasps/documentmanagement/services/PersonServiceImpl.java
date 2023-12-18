package sasps.documentmanagement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.controllers.interfaces.PersonService;
import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.dtos.adapter.PersonAdapter;
import sasps.documentmanagement.entities.Person;
import sasps.documentmanagement.exceptions.PersonNotFoundException;
import sasps.documentmanagement.repos.PersonRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl( PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public UUID createPerson(PersonDTO personDTO) {
        Person person = PersonAdapter.toPerson(personDTO);
        Person savedPerson = personRepository.save(person);
        return savedPerson.getId();
    }

    public PersonDTO findPersonByID(UUID id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isEmpty()){
            LOGGER.error("Person with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Person with id("+id+") doesn't exist!");
        }
        Person person = personOptional.get();
        return PersonAdapter.toPersonDTO(person);
    }

    public UUID deletePerson(UUID id) throws PersonNotFoundException {
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isEmpty()){
            LOGGER.error("Person with id("+id+") doesn't exist!");
            throw new PersonNotFoundException("Person with id("+id+") doesn't exist!");
        }
        Person person = personOptional.get();
        personRepository.delete(person);
        return person.getId();
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

    public UUID updatePerson(UUID id, PersonDTO personDTO){
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isEmpty()){
            LOGGER.error("Person with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Person with id("+id+") doesn't exist!");
        }
        Person person = personOptional.get();
        person.setUsername(personDTO.getUsername());
        person.setPassword(personDTO.getPassword());
        person.setRole(personDTO.getRole());
        Person updatedPerson = personRepository.save(person);
        return updatedPerson.getId();
    }

    @Override
    public UUID proxyCreatePerson(PersonDTO personDTO) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public PersonDTO proxyFindPersonByID(UUID id, String token) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID proxyDeletePerson(UUID id, String token) throws PersonNotFoundException {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }

    @Override
    public UUID proxyUpdatePerson(UUID id, PersonDTO personDTO) {
        throw new UnsupportedOperationException("This method is not allowed in this class!");
    }
}
