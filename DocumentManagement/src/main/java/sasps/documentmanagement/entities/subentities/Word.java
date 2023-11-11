package sasps.documentmanagement.entities.subentities;

import jakarta.persistence.DiscriminatorValue;
import sasps.documentmanagement.entities.Document;

@DiscriminatorValue(".docx")
public class Word extends Document {
    public Word() {
        super();
    }
}
