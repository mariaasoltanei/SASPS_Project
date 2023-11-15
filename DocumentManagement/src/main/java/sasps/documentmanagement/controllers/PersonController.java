package sasps.documentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.services.PersonService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPersonByID(@PathVariable UUID id){
        PersonDTO personDTO = personService.findPersonByID(id);
        return new ResponseEntity<>(personDTO, HttpStatus.FOUND);
    }

    @PostMapping("/createPerson")
    public ResponseEntity<?> createPerson(PersonDTO personDTO) {
        UUID createdPersonID = personService.createPerson(personDTO);
        return new ResponseEntity<>(createdPersonID, HttpStatus.CREATED);
    }
}
