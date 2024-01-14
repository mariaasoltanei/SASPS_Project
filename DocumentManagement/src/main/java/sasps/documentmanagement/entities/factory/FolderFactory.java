package sasps.documentmanagement.entities.factory;

import sasps.documentmanagement.entities.DocumentComponent;
import sasps.documentmanagement.entities.Extension;
import sasps.documentmanagement.entities.Folder;
import sasps.documentmanagement.entities.Person;

import java.util.Date;
import java.util.UUID;

public class FolderFactory extends DocumentComponentFactory{
    @Override
    public DocumentComponent createDocumentComponent(String name, Date uploadDate, Date lastModified, Person person, String extension) {
        UUID id = UUID.randomUUID();
        return new Folder(null);
    }
}
