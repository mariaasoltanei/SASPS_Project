package sasps.antipatterndocumentmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

// Numai eu si Dumnezeu stim ce face clasa asta
// Un nume mai bun ar fi fost "Handler" sau "Service"
// Updateaza aici acest counter daca te-ai dat batut din a incerca sa intelegi ce face clasa asta
// Counter: 1
@Service
@RestController
@RequestMapping("/antipattern/v1")
@Controller
public class GodClass {
    Utils utils;
    Poltergeist poltergeist;



    @Autowired
    public GodClass(Poltergeist poltergeist, Utils utils)
    {
        this.poltergeist = poltergeist;
        this.utils = utils;
    }

    public Document findByID(UUID id) {
        try {
            if (id == null)
                throw new IllegalArgumentException("Id is null!");
            return poltergeist.repos.documentRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public UUID createDocument(Document document, Person person) {
        try {
            Document searchedDocument = poltergeist.repos.documentRepository.findByName(document.getName()).orElse(null);
            if (searchedDocument != null)
                throw new IllegalArgumentException("Document with name(" + document.getName() + ") already exists!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        document.setPerson(person);
        Document savedDocument = poltergeist.repos.documentRepository.save(document);
        return savedDocument.getId();
    }
    public UUID deleteDocument (UUID id) {
        Optional<Document> documentOptional = poltergeist.repos.documentRepository.findById(id);
        if (documentOptional.isEmpty()) {
            System.out.println("Document with id(" + id + ") doesn't exist!");
            return null;
        }
        else
        {
            Document document = documentOptional.get();
            poltergeist.repos.documentRepository.delete(document);
            return document.getId();
        }
    }

    public UUID updateDocument (UUID id, Document document) {
        Optional<Document> documentOptional = poltergeist.repos.documentRepository.findById(id);
        if (documentOptional.isEmpty()) {
            System.out.println("Document with id(" + id + ") doesn't exist!");
            return null;
        }
        else {
            Document updatedDocument = documentOptional.get();
            updatedDocument.setName(document.getName());
            updatedDocument.setUploadDate(document.getUploadDate());
            updatedDocument.setLastModified(document.getLastModified());
            document.setPerson(document.getPerson());
            poltergeist.repos.documentRepository.save(updatedDocument);
            return updatedDocument.getId();
        }
    }

    public UUID createPerson(Person person) {
        Person savedPerson = poltergeist.repos.personRepository.save(person);
        return savedPerson.getId();
    }

    public Person findPersonByID(UUID id) {
        Optional<Person> personOptional = poltergeist.repos.personRepository.findById(id);
        if(personOptional.isEmpty()){
            System.out.println("Person with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Person with id("+id+") doesn't exist!");
        }
        else
            return personOptional.get();
    }

    public UUID deletePerson(UUID id) /* throws PersonNotFoundException */{
        Optional<Person> personOptional = poltergeist.repos.personRepository.findById(id);
        if(personOptional.isEmpty()){
            System.out.println("Person with id("+id+") doesn't exist!");
            return null;
        }
        else{
            Person person = personOptional.get();
            poltergeist.repos.personRepository.delete(person);
            return person.getId();
        }
    }

    public void deleteAll() {
        poltergeist.repos.personRepository.deleteAll();
    }

    public UUID updatePerson(UUID id, Person person){
        Optional<Person> personOptional = poltergeist.repos.personRepository.findById(id);
        if(personOptional.isEmpty()){
            System.out.println("Person with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Person with id("+id+") doesn't exist!");
        }
        else{
            Person updatedPerson = personOptional.get();
            updatedPerson.setUsername(person.getUsername());
            updatedPerson.setPassword(person.getPassword());
            updatedPerson.setRole(person.getRole());
            updatedPerson.setSignature(person.getSignature());
            poltergeist.repos.personRepository.save(updatedPerson);
            return updatedPerson.getId();
        }
    }

    @GetMapping(path = "/document/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable UUID id) {
        Document document = findByID(id);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @GetMapping("/document/{id}/")
    public ResponseEntity<Document> findByIdEndpoint(@PathVariable String id) {
        Document document = findByID(UUID.fromString(id));
        return new ResponseEntity<>(document, HttpStatus.FOUND);
    }

    @PostMapping("/document/create")
    public ResponseEntity<UUID> createDocumentEndpoint(@RequestBody Document document) {
        UUID createdDocumentID = createDocument(document, null);
        return new ResponseEntity<>(createdDocumentID, HttpStatus.CREATED);
    }

    @DeleteMapping("/document/delete/{id}")
    public ResponseEntity<?> deleteDocumentEndpoint(@PathVariable UUID id) throws Exception {
        UUID deletedDocumentID = deleteDocument(id);
        return new ResponseEntity<>(deletedDocumentID, HttpStatus.GONE);
    }

    @PutMapping("/document/update/{id}")
    public ResponseEntity<?> updateDocumentEndpoint(@PathVariable UUID id, @RequestBody Document document) throws Exception {
        UUID updatedDocumentID = updateDocument(id,document);
        return new ResponseEntity<>(updatedDocumentID, HttpStatus.ACCEPTED);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<?> findPersonByIDEndpoint(@PathVariable UUID id){
        Person person = findPersonByID(id);
        return new ResponseEntity<>(person, HttpStatus.FOUND);
    }

    @PostMapping("/person/create")
    public ResponseEntity<?> createPersonEndpoint(@RequestBody Person person) {
        UUID createdPersonID = createPerson(person);
        return new ResponseEntity<>(createdPersonID, HttpStatus.CREATED);
    }

    @DeleteMapping("/person/delete/{id}")
    public ResponseEntity<?> deletePersonEndpoint(@PathVariable UUID id) {
        UUID deletedPersonID = deletePerson(id);
        return new ResponseEntity<>(deletedPersonID, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/person/deleteAll")
    public ResponseEntity<?> deleteAllEndpoint(){
        deleteAll();
        return new ResponseEntity<>("All users deleted!",HttpStatus.OK);
    }
    @PutMapping("/person/update/{id}")
    public ResponseEntity<?> updatePersonEndpoint(@PathVariable UUID id, @RequestBody Person person){
        UUID updatedPersonID = updatePerson(id, person);
        return new ResponseEntity<>(updatedPersonID, HttpStatus.ACCEPTED);
    }
}
