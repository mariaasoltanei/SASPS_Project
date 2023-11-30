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

public interface DocumentFactory {
    static Document createDocument(String name, Date uploadDate, Date lastModified, Person person, String extension)
    {
        UUID id = UUID.randomUUID();
        return switch(extension){
            case ".docx" -> new Word(id,name,uploadDate,lastModified,person);
            case ".pdf" -> new PDF(id,name,uploadDate,lastModified,person);
            case ".xls" -> new Excel(id,name,uploadDate,lastModified,person);
            case ".txt" -> new Text(id,name,uploadDate,lastModified,person);
            default -> throw new IllegalArgumentException("Document with extension "+extension+" is not supported!");
        };
    }

    static Folder createFolder(String name, List<DocumentComponent> documents){
        return new Folder(name,documents);
    }
}
