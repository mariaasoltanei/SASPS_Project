package sasps.documentmanagement.dtos;

import lombok.*;
import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {
    UUID id;
    String username;
    String password;
    Role role;
    List<Document> documents = new ArrayList<>();

}
