package tue.horse.process.core.accessor;

import javafx.concurrent.Task;
import org.camunda.bpm.engine.cdi.BusinessProcess;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ktraganos on 7-3-2017.
 */
public class CoreDataAccessor {

    @Inject
    private BusinessProcess businessProcess;

    public static final String ProductNo = "productNo";

    public static final String AssigneeRepeatProcessTask = "assigneeRepeatProcessTask";
    public static final String TaskIdRepeatProcess = "taskIdRepeatProcess";


    public static final String TASKS = "tasks";


    public String getProductNo() {
        return businessProcess.getVariable(ProductNo);
    }
    public void setProductNo(String productNo) {
        businessProcess.setVariable(ProductNo, productNo);
    }

    public String getAssigneeRepeatProcessTask() {
        return businessProcess.getVariable(AssigneeRepeatProcessTask);
    }
    public void setAssigneeRepeatProcessTask(String assigneeRepeatProcessTask) {
        businessProcess.setVariable(AssigneeRepeatProcessTask, assigneeRepeatProcessTask);
    }

    public String getTaskIdRepeatProcess() {
        return businessProcess.getVariable(TaskIdRepeatProcess);
    }
    public void setTaskIdRepeatProcess(String taskIdRepeatProcess) {
        businessProcess.setVariable(TaskIdRepeatProcess, taskIdRepeatProcess);
    }

    public Map<String, String> getTasks() {
        Map<String,String> tasks = new HashMap<String, String>();
        tasks = businessProcess.getVariable(TASKS);
        if (tasks == null) {
            return businessProcess.getVariable("tasks");}
        else {return tasks;}
    }
    public void setTasks(Map<String, String> tasks) {
        businessProcess.setVariable(TASKS, tasks);
    }

}
