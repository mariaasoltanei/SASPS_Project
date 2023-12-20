package sasps.documentmanagement.entities.factory;

import sasps.documentmanagement.entities.DocumentComponent;
import sasps.documentmanagement.entities.Person;

import java.util.Date;

public abstract class DocumentComponentFactory {
    public abstract DocumentComponent createDocumentComponent(String name, Date uploadDate, Date lastModified, Person person, String extension);
}
