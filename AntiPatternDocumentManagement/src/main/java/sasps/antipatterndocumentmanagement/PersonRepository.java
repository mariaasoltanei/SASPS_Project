package sasps.antipatterndocumentmanagement;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends org.springframework.data.jpa.repository.JpaRepository<Person, java.util.UUID> {
    Person findByUsername(String username);
}
