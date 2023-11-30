package sasps.documentmanagement.dtos;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sasps.documentmanagement.entities.Person;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    String name;
    Date uploadDate;
    Date lastModifiedDate;
    Person person;
    String extension;
}
