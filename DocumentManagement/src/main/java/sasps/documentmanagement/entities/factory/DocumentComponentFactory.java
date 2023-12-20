package sasps.documentmanagement.entities.factory;

import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.DocumentComponent;
import sasps.documentmanagement.entities.Folder;
import sasps.documentmanagement.entities.Person;
import sasps.documentmanagement.entities.subentities.Excel;
import sasps.documentmanagement.entities.subentities.PDF;
import sasps.documentmanagement.entities.subentities.Text;
import sasps.documentmanagement.entities.subentities.Word;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public abstract class DocumentFactory {
    DocumentComponent createDocumentComponent(String name, Date uploadDate, Date lastModified, Person person, String extension);


}
