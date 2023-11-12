package sasps.documentmanagement.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.DocumentComponent;
import sasps.documentmanagement.entities.Person;
import sasps.documentmanagement.entities.Role;

import java.util.HashMap;

public abstract class DocumentProxy implements DocumentComponent {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentProxy.class);
    private Document document;
    private Person person;
    private HashMap<Person, Document> accessList = new HashMap<>();
    public DocumentProxy(Document document, Person person) {
        this.document = document;
        this.person = person;
    }
    private Person authenticatePerson(String username, String password) {
        if(person != null && person.getPassword().equals(password) && person.getUsername().equals(username)){
            if (person.getUsername().equals("admin")) {
                person.setRole(Role.ROLE_ADMIN);
            } else {
                person.setRole(Role.ROLE_ADMIN);
            }
        }
        return person;
    }

    private void logAccess() {
        LOGGER.info("Document accessed by: " + person.getUsername());
    }
    public void grantAccess(Person person, Document document) {
        if(this.person.getRole().equals(Role.ROLE_ADMIN)){
            LOGGER.info("Access granted to: " + person.getUsername());
            accessList.put(person, document);
        }
    }
    public void revokeAccess(Person person, Document document) {
        if(this.person.getRole().equals(Role.ROLE_ADMIN)){
            if(accessList.containsKey(person) && accessList.get(person).equals(document)){
                LOGGER.info("Access remove for: " + person.getUsername());
                accessList.remove(person, document);
            }
            else{
                LOGGER.info("Specified access dos not exist.");
            }
        }
    }
}
