package sasps.documentmanagement.dtos.adapter;

import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.entities.Person;

import java.util.UUID;

public class PersonAdapter {

    // Used only by createPerson() method
    // Safe to define documents list as "null"
    public static Person toPerson(PersonDTO personDTO) {
        return new Person(UUID.randomUUID(), personDTO.getUsername(), personDTO.getPassword(), personDTO.getRole(), personDTO.getSignature(), null);
    }

    public static PersonDTO toPersonDTO(Person person) {
        return new PersonDTO(person.getUsername(), person.getPassword(), person.getRole(),person.getSignature(), null);
    }
}
