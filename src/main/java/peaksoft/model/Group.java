package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq",
            sequenceName = "group_seq",
            allocationSize = 1)
    @Column(length = 100000)
    private Long id;

    @Column(length = 100000)
    private String groupName;

    @Column(length = 100000)
    private String dateOfStart;

    @Column(length = 100000)
    private String image;


    @ManyToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinTable(
            name = "groups_courses",
            joinColumns = @JoinColumn(name = "groupId"),
            inverseJoinColumns = @JoinColumn(name = "courseId"))
    private List<Course> courses;
    public void addCourse(Course course){
        if (courses==null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

    @OneToMany(cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.REFRESH , CascadeType.REMOVE}
            , mappedBy = "group")
    private List<Student>students;
    public void addStudent(Student student){
        if (students==null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
