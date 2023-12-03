package sasps.documentmanagement.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.controllers.interfaces.PersonService;
import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.dtos.builder.PersonBuilder;
import sasps.documentmanagement.entities.Person;
import sasps.documentmanagement.exceptions.PersonNotFoundException;

import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final UnitOfWorkImpl unitOfWork;

    @Autowired
    public PersonServiceImpl(UnitOfWorkImpl unitOfWork){
        this.unitOfWork = unitOfWork;
    }

    public UUID createPerson(PersonDTO personDTO) {
        Person person = PersonBuilder.toPerson(personDTO);
        Person savedPerson = unitOfWork.getPersonRepository().save(person);
        return savedPerson.getId();
    }

    public PersonDTO findPersonByID(UUID id) {
        Optional<Person> personOptional = unitOfWork.getPersonRepository().findById(id);
        if(personOptional.isEmpty()){
            LOGGER.error("Person with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Person with id("+id+") doesn't exist!");
        }
        Person person = personOptional.get();
        return PersonBuilder.toPersonDTO(person);
    }

    public UUID deletePerson(UUID id) throws PersonNotFoundException {
        Optional<Person> personOptional = unitOfWork.getPersonRepository().findById(id);
        if(personOptional.isEmpty()){
            LOGGER.error("Person with id("+id+") doesn't exist!");
            throw new PersonNotFoundException("Person with id("+id+") doesn't exist!");
        }
        Person person = personOptional.get();
        unitOfWork.getPersonRepository().delete(person);
        return person.getId();
    }

    public void deleteAll() {
        unitOfWork.getPersonRepository().deleteAll();
    }

    public UUID updatePerson(UUID id, PersonDTO personDTO){
        Optional<Person> personOptional = unitOfWork.getPersonRepository().findById(id);
        if(personOptional.isEmpty()){
            LOGGER.error("Person with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Person with id("+id+") doesn't exist!");
        }
        Person person = personOptional.get();
        person.setUsername(personDTO.getUsername());
        person.setPassword(personDTO.getPassword());
        person.setRole(personDTO.getRole());
        Person updatedPerson = unitOfWork.getPersonRepository().save(person);
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
