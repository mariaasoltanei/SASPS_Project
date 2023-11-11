package sasps.documentmanagement.dtos;

import lombok.*;

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
}
