package sasps.documentmanagement.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    UUID id;
    String name;
    byte[] content;
    Date uploadDate;
    Date lastModifiedDate;
}
