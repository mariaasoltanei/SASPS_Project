package sasps.documentmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "extension")
@Entity
@Builder
@Table(name = "document")
public class Document implements DocumentComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "upload_date",nullable = false)
    private Date uploadDate;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Override
    public String display() {
        return this.toString();
    }
}
