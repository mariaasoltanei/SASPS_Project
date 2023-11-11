package sasps.documentmanagement.entities.subentities;

import jakarta.persistence.DiscriminatorValue;
import sasps.documentmanagement.entities.Document;

@DiscriminatorValue(".xls")
public class Excel extends Document {
    public Excel(){
        super();
    }
}
