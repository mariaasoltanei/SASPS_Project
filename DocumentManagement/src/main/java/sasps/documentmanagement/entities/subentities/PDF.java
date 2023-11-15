package sasps.documentmanagement.entities.subentities;

import jakarta.persistence.DiscriminatorValue;
import sasps.documentmanagement.entities.Document;

import java.util.UUID;

@DiscriminatorValue(".pdf")
public class PDF extends Document {
    public PDF(){
        super();
    }
}
