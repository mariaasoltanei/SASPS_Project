package sasps.antipatterndocumentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class Repos {

    DocumentRepository documentRepository;
    PersonRepository personRepository;

    @Autowired
    public Repos(DocumentRepository documentRepository, PersonRepository personRepository) {
        this.documentRepository = documentRepository;
        this.personRepository = personRepository;
    }
}
