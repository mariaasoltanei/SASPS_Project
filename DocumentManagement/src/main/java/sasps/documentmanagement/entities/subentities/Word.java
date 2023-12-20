package sasps.documentmanagement.entities.subentities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.Person;

import java.util.Date;
import java.util.UUID;

@DiscriminatorValue(".docx")
@Entity
@NoArgsConstructor
public class Word extends Document {
    public Word(UUID id, String name, Date uploadDate, Date lastModified, Person person){
        super(id, name, uploadDate, lastModified, person);
    }
}
