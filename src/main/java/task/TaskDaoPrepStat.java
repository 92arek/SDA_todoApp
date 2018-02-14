package task;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDaoPrepStat {

    public static final String INSERT = "INSERT INTO tasks (name) VALUES (?)";
    public static final String MARK_COMPLETED = "UPDATE tasks SET completed_by = ?, " +
            "completed_at=now() WHERE id = ?";
    public static final String REMOVE_COMPLETED = "DELETE FROM tasks WHERE completed_by IS NOT NULL";
    private DataSource ds;

    public TaskDaoPrepStat(DataSource ds) {
        this.ds = ds;
    }

    public void save2(String name) throws SQLException {
        PreparedStatement ps = ds.getConnection().prepareStatement(INSERT);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
    }

    public void markAsDone2(Long taskId, Long userId) throws SQLException {
        PreparedStatement ps2 = ds.getConnection().prepareStatement(MARK_COMPLETED);
        ps2.setLong(1, userId);
        ps2.setLong(2, taskId);
        ResultSet rs2 = ps2.executeQuery();
    }

    public void removeCompleted2() throws SQLException {
        PreparedStatement ps3 = ds.getConnection().prepareStatement(REMOVE_COMPLETED);
        ResultSet rs3 = ps3.executeQuery();
    }
}
