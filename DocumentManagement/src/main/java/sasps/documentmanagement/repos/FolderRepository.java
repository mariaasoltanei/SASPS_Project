package sasps.documentmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import sasps.documentmanagement.entities.Folder;

import java.util.UUID;

public interface FolderRepository extends JpaRepository<Folder, UUID> {
}
