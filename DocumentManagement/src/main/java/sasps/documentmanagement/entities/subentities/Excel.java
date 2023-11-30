package sasps.documentmanagement.entities.subentities;

import jakarta.persistence.DiscriminatorValue;
import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.Person;

import java.util.Date;
import java.util.UUID;

@DiscriminatorValue(".xls")
public class Excel extends Document {
    public Excel(UUID id, String name, Date uploadDate, Date lastModified, Person person){
        super(id,name,uploadDate,lastModified,person);
    }
}
