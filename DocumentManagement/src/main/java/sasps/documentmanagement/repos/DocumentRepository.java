package sasps.documentmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {
    Optional<Document> findById(UUID id);

    Optional<Document> findByName(String name);

    Optional<List<Document>> findByPerson(Person person);
}
