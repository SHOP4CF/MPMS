package tue.horse.entity;

import javax.persistence.*;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"task_unique_id"})})
public class TaskDef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;

    @Column
    private String task_modeler_id;

    @Column
    private String task_unique_id;

    @Column
    private String task_name;

    @Column
    private Long task_process_id;

    @Column
    private String task_description;

    @Column
    private long task_role_id;

    @Column
    private String task_location;

    @Column
    private long task_duration;


    public Long getTaskId() {
        return task_id;
    }

    public void setTaskId(Long TaskId) {
        this.task_id= TaskId;
    }

    public String getTaskModelerId() {
        return task_modeler_id;
    }

    public void setTaskModelerId(String TaskModelerId) {
        this.task_modeler_id = task_modeler_id;
    }

    public String getTaskUniqueId() {
        return task_unique_id;
    }

    public void setTaskUniqueId(String TaskUniqueId) {
        this.task_unique_id= TaskUniqueId;
    }

    public String getTaskName() {
        return task_name;
    }

    public void setTaskName(String TaskName) {
        this.task_name = TaskName;
    }

    public Long getTaskProcessId() {
        return task_process_id;
    }

    public void setTaskProcessId(Long TaskProcessId) {
        this.task_process_id= TaskProcessId;
    }

    public String getTaskDescription() {
        return task_description;
    }

    public void setTaskDescription(String task_description) {
        this.task_description = task_description;
    }

    public long getTaskRoleId() {
        return task_role_id;
    }

    public void setTaskRole(long task_role_id) {
        this.task_role_id = task_role_id;
    }

    public String getTaskLocation() {
        return task_location;
    }

    public void setTaskLocation(String task_location) {
        this.task_location = task_location;
    }

    public long getTaskDuration() {
        return task_duration;
    }

    public void setTaskDuration(long task_duration) {
        this.task_duration = task_duration;
    }
}
