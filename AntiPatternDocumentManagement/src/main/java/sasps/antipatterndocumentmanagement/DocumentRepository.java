package sasps.antipatterndocumentmanagement;

import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends org.springframework.data.jpa.repository.JpaRepository<Document, java.util.UUID> {
    java.util.Optional<Document> findById(java.util.UUID id);

    java.util.Optional<Document> findByName(String name);

    java.util.Optional<java.util.List<Document>> findByPerson(Person person);
}
