package sasps.antipatterndocumentmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class Poltergeist {

    ////////////////////////////////
    /////// Repository classes ////
    //////////////////////////////
    public Repositories repositories;

    @Autowired
    public Poltergeist(Repositories repositories)
    {
        this.repositories = repositories;
    }
}
