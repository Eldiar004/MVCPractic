package peaksoft.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "instructors")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    @SequenceGenerator(name = "instructor_seq",
            sequenceName = "instructor_seq",
            allocationSize = 1)
    @Column(length = 100000)
    private Long id;

    @Column(length = 100000)
    private String firstName;

    @Column(length = 100000)
    private String lastName;

    @Column(length = 100000)
    private String phoneNumber;

    @Column(length = 100000)
    private String email;

    @Column(length = 100000)
    private String specialization;


    @ManyToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.REFRESH})
    private Course course;
}
