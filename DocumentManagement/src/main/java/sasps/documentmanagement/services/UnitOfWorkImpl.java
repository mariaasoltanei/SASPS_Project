package sasps.documentmanagement.services;

import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sasps.documentmanagement.repos.DocumentRepository;
import sasps.documentmanagement.repos.PersonRepository;
import sasps.documentmanagement.repos.UnitOfWork;

@Service
@Getter
public class UnitOfWorkImpl implements UnitOfWork {
    private final PersonRepository personRepository;
    private final DocumentRepository documentRepository;

    @Autowired
    public UnitOfWorkImpl(PersonRepository personRepository, DocumentRepository documentRepository){
        this.personRepository = personRepository;
        this.documentRepository = documentRepository;
    }
    @Override
    @Transactional
    public void beginTransaction() {

    }

    @Override
    @Transactional
    public void commit() {
    }

    @Override
    @Transactional
    public void rollback() {
    }
}
