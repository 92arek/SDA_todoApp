package task;

import org.jooq.DSLContext;


import static com.todo.db.tables.Tasks.TASKS;

public class TaskDaoJooq {

    public final DSLContext dslc2;

    public TaskDaoJooq(DSLContext dslc2) {
        this.dslc2 = dslc2;
    }


    public void save(String name) {
        dslc2.insertInto(TASKS).columns(TASKS.NAME).values(name).execute();
    }

    public void markAsDone(Integer userId, Integer taskId){
        dslc2.update(TASKS).set(TASKS.COMPLETED_BY, userId).set(TASKS.ID, taskId).execute();
    }

    public void removeCompleted(){
        dslc2.deleteFrom(TASKS).where(TASKS.COMPLETED_BY != null).execute();
    }
}
