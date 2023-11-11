package sasps.documentmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sasps.documentmanagement.entities.Document;

import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<UUID, Document> {
}
