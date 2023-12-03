package sasps.documentmanagement.dtos;

import lombok.*;
import sasps.documentmanagement.entities.Role;

import java.security.KeyPair;

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
    String token;
}
