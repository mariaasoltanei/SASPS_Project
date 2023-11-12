package sasps.documentmanagement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.dtos.builder.PersonBuilder;
import sasps.documentmanagement.entities.Person;
import sasps.documentmanagement.repos.PersonRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public UUID createPerson(PersonDTO personDTO) {
        Person person = PersonBuilder.toPerson(personDTO);
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
        return PersonBuilder.toPersonDTO(person);
    }

    public UUID deletePerson(UUID id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isEmpty()){
            LOGGER.error("Person with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Person with id("+id+") doesn't exist!");
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
        person.setDocuments(personDTO.getDocuments());
        Person updatedPerson = personRepository.save(person);
        return updatedPerson.getId();
    }
}
