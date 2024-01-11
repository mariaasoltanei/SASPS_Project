package sasps.documentmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.DocumentComponent;
import sasps.documentmanagement.entities.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentComponent, UUID> {
    Optional<DocumentComponent> findById(UUID id);

    Optional<DocumentComponent> findByName(String name);

    Optional<List<DocumentComponent>> findByPerson(Person person);
}
