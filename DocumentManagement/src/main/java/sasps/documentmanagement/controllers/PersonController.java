package sasps.documentmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sasps.documentmanagement.dtos.PersonDTO;
import sasps.documentmanagement.exceptions.PersonNotFoundException;
import sasps.documentmanagement.services.PersonServiceImpl;

import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class PersonController {
    private final PersonServiceImpl personServiceImpl;

    @Autowired
    public PersonController(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPersonByID(@PathVariable UUID id){
        PersonDTO personDTO = personServiceImpl.findPersonByID(id);
        return new ResponseEntity<>(personDTO, HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPerson(@RequestBody PersonDTO personDTO) {
        UUID createdPersonID = personServiceImpl.createPerson(personDTO);
        return new ResponseEntity<>(createdPersonID, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable UUID id) throws PersonNotFoundException {
        UUID deletedPersonID = personServiceImpl.deletePerson(id);
        return new ResponseEntity<>(deletedPersonID, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll(){
        personServiceImpl.deleteAll();
        return new ResponseEntity<>("All users deleted!",HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable UUID id, @RequestBody PersonDTO personDTO){
        UUID updatedPersonID = personServiceImpl.updatePerson(id, personDTO);
        return new ResponseEntity<>(updatedPersonID, HttpStatus.ACCEPTED);
    }
}
