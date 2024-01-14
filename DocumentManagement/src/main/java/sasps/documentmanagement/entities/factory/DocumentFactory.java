package sasps.documentmanagement.entities.factory;

import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.DocumentComponent;
import sasps.documentmanagement.entities.Extension;
import sasps.documentmanagement.entities.Person;

import java.util.Date;
import java.util.UUID;

public class DocumentFactory extends DocumentComponentFactory{
    @Override
    public DocumentComponent createDocumentComponent(String name, Date uploadDate, Date lastModified, Person person, String extension) {
        {
            UUID id = UUID.randomUUID();
            return switch(extension){
                case ".docx" -> new Document(id,name,uploadDate,lastModified,Extension.Word,person);
                case ".pdf" -> new Document(id,name,uploadDate,lastModified,Extension.PDF,person);
                case ".xls" -> new Document(id,name,uploadDate,lastModified,Extension.Excel,person);
                case ".txt" -> new Document(id,name,uploadDate,lastModified,Extension.Text,person);
                case ".ppt" -> new Document(id,name,uploadDate,lastModified,Extension.PowerPoint,person);
                default -> throw new IllegalArgumentException("Document with extension "+extension+" is not supported!");
            };
        }
    }
}
