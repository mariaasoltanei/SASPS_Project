package sasps.documentmanagement.dtos;

import lombok.*;
import sasps.documentmanagement.entities.Document;
import sasps.documentmanagement.entities.Role;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {
    String username;
    String password;
    Role role;
    KeyPair signature;
    List<Document> documents = new ArrayList<>();

}
