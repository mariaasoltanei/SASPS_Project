package sasps.documentmanagement.repos;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface UnitOfWork {
    void beginTransaction();
    void commit();
    void rollback();
}
