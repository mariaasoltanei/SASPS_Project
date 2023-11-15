package sasps.documentmanagement.dtos.builder;

import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.entities.Person;

public class PersonBuilder {
    public static Person toPerson(PersonDTO personDTO) {
        return new Person(personDTO.getId(), personDTO.getUsername(), personDTO.getPassword(), personDTO.getRole(), personDTO.getDocuments());
    }

    public static PersonDTO toPersonDTO(Person person) {
        return new PersonDTO(person.getId(), person.getUsername(), person.getPassword(), person.getRole(), person.getDocuments());
    }
}
