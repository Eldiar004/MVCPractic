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
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq",
            sequenceName = "lesson_seq",
            allocationSize = 1)
    @Column(length = 100000)
    private Long id;

    @Column(length = 100000)
    private String lessonName;

    @ManyToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.REFRESH})
    private Course course;

    @OneToMany(mappedBy = "lesson" , cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.REMOVE , CascadeType.REFRESH})
    private List<Task> tasks;
    public void addTask(Task task){
        if (tasks==null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }
}

