package sasps.documentmanagement.dtos;


import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
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
    String extension;
    String token;
}
