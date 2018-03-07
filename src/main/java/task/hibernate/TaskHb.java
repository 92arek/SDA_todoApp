package task.hibernate;

import com.todo.user.hibernate.UserHb;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name="TASKS")
public class TaskHb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column (name = "task_name")
    public String taskName;

    @Column (name = "created_at")
    public LocalDate createdAt;

    @Column (name = "completed_at")
    public LocalDate completedAt;

    @Column (name = "completed_by")
    public UserHb completedBy;

@ManyToMany (fetch = EAGER, mappedBy = "tasks")
    public List<UserHb> users;

    public TaskHb(String taskName) {
        this.taskName = taskName;
    }
}
