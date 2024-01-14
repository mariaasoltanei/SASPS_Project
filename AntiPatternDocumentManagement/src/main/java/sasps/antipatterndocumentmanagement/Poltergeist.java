package sasps.antipatterndocumentmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Poltergeist {

    ////////////////////////////////
    /////// Repository classes ////
    //////////////////////////////
    public Repos repos;

    @Autowired
    public Poltergeist(Repos repos)
    {
        this.repos = repos;
    }
}
