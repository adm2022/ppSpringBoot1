package adm2022.pp.domain;

import java.util.Set;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Story {

    // Let us add a constructor here
    public Story() {
        dateCreation = new Date();
    }
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String summary;
    @Column(nullable = false)
    private Date dateCreation;
    @Column(columnDefinition = "clob")
    private String description;

    @Column
    private Integer points;

    @OneToMany(mappedBy = "meetingStories")
    private Set<Meeting> meetingStoriesMeetings;

}
