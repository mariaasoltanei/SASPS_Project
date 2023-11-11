package sasps.documentmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "extension")
@Builder
@Table(name = "Document")
public class Document implements DocumentComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Lob
    @Column(name = "content", nullable = false)
    private byte[] content;

    @Column(name = "upload_date",nullable = false)
    private Date uploadDate;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Override
    public String display() {
        return this.toString();
    }
}
