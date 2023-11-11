package sasps.documentmanagement.dtos.builder;

import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.entities.Person;

public class PersonBuilder {
    private static Person toPerson(PersonDTO personDTO) {
        return new Person(personDTO.getId(), personDTO.getUsername(), personDTO.getPassword());
    }

    private static PersonDTO toPersonDTO(Person person){
        return new PersonDTO(person.getUserId(), person.getUsername(), person.getPassword());
    }
}
