package sasps.antipatterndocumentmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RestController
@RequestMapping("/v1")
@Controller
public class Handler_GodClass {
    DocumentRepository documentRepository;
    PersonRepository personRepository;
    @Autowired
    public Handler_GodClass(DocumentRepository documentRepository, PersonRepository personRepository)
    {
        this.documentRepository = documentRepository;
        this.personRepository = personRepository;
    }
    @Repository
    interface DocumentRepository extends JpaRepository<Document, UUID> {
        Optional<Document> findById(UUID id);

        Optional<Document> findByName(String name);

        Optional<List<Document>> findByPerson(Person person);
    }

    @Repository
    interface PersonRepository extends JpaRepository<Person, UUID> {
        Person findByUsername(String username);
    }

    public Document getDocumentByID(UUID id) {
        return documentRepository.findById(id).orElse(null);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable UUID id) {
        Document document = getDocumentByID(id);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    public Document findByID(UUID id) {
        try {
            if (id == null)
                throw new IllegalArgumentException("Id is null!");
            Document document = findByID(id);
            return document;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public UUID createDocument(Document document, Person person) {
        try {
            Document searchedDocument = documentRepository.findByName(document.getName()).orElse(null);
            if (searchedDocument != null)
                throw new IllegalArgumentException("Document with name(" + document.getName() + ") already exists!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        document.setPerson(person);
        Document savedDocument = documentRepository.save(document);
        return savedDocument.getId();
    }
    public UUID deleteDocument (UUID id) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        if (documentOptional.isEmpty()) {
            System.out.println("Document with id(" + id + ") doesn't exist!");
        }
        Document document = findByID(id);
        documentRepository.delete(document);
        return document.getId();
    }

    public UUID updateDocument (UUID id, Document document) {
        Optional<Document> documentOptional = documentRepository.findById(id);
        Document documentOPT = documentOptional.get();
        document.setName(document.getName());
        document.setUploadDate(document.getUploadDate());
        document.setLastModified(document.getLastModified());
        //document.setPerson(document.getPerson());
        Document updatedDocument = documentRepository.save(document);
        return updatedDocument.getId();
    }

    public UUID createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return savedPerson.getId();
    }

    public Person findPersonByID(UUID id) {
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isEmpty()){
            System.out.println("Person with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Person with id("+id+") doesn't exist!");
        }
        Person person = personOptional.get();
        return person;
    }

    public UUID deletePerson(UUID id) /* throws PersonNotFoundException */{
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isEmpty()){
            System.out.println("Person with id("+id+") doesn't exist!");
        }
        Person person = personOptional.get();
        personRepository.delete(person);
        return person.getId();
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

    public UUID updatePerson(UUID id, Person person){
        Optional<Person> personOptional = personRepository.findById(id);
        if(personOptional.isEmpty()){
            System.out.println("Person with id("+id+") doesn't exist!");
            throw new ResourceNotFoundException("Person with id("+id+") doesn't exist!");
        }
        Person updatedPerson = personRepository.save(person);
        return updatedPerson.getId();
    }

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtConfig jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception{
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        } catch (DisabledException ex){
            throw new Exception("USER_DISABLED", ex);
        } catch (BadCredentialsException ex){
            throw new Exception("INVALID_CREDENTIALS", ex);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody Person person) {
        return ResponseEntity.ok(personRepository.save(person));
    }

    @GetMapping("/document/{id}/{token}")
    public ResponseEntity<Document> findById(@PathVariable String id, @RequestBody String token) throws Exception {
        Document document = findByID(UUID.fromString(id), token);
        return new ResponseEntity<>(document, HttpStatus.FOUND);
    }

    @PostMapping("/document/create")
    public ResponseEntity<UUID> createDocumentEndpoint(@RequestBody Document document) throws Exception {
        UUID createdDocumentID = createDocument(document, null);
        return new ResponseEntity<>(createdDocumentID, HttpStatus.CREATED);
    }

    @DeleteMapping("/document/delete/{id}")
    public ResponseEntity<?> deleteDocumentEndpoint(@PathVariable UUID id, @RequestBody String token) throws Exception {
        UUID deletedDocumentID = deleteDocument(id,token);
        return new ResponseEntity<>(deletedDocumentID, HttpStatus.GONE);
    }

    @PutMapping("/document/update/{id}")
    public ResponseEntity<?> updateDocumentEndpoint(@PathVariable UUID id, @RequestBody Document document, @RequestBody String token) throws Exception {
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
    public ResponseEntity<?> deletePersonEndpoint(@PathVariable UUID id) /* throws PersonNotFoundException */ {
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
