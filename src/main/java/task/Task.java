package task;

import com.todo.user.User;

import java.time.LocalDate;

public class Task {

    private long id;
    private String name;
    private LocalDate created_at;
    private LocalDate completed_at;
    private User completed_by;

    public Task(long id, String name, LocalDate created_at) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created_at=" + created_at +
                ", completed_at=" + completed_at +
                ", completed_by=" + completed_by +
                '}';
    }
}
