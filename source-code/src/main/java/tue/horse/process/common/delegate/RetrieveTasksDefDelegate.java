package tue.horse.process.common.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import tue.horse.business.ProcessDefBusinessService;
import tue.horse.business.TaskDefBusinessService;
import tue.horse.entity.ProcessDef;
import tue.horse.entity.TaskDef;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by ktraganos on 24-3-2017.
 */
@Named
public class RetrieveTasksDefDelegate {

    private final Logger LOGGER = Logger.getLogger(RetrieveTasksDefDelegate.class.getName());


    public Map<String, String> execute(DelegateExecution execution, ProcessDefBusinessService processDefBusinessService, TaskDefBusinessService taskDefBusinessService ) throws Exception {

        List<ProcessDef> processEntities = new ArrayList<ProcessDef>();
        List<TaskDef> taskEntities = new ArrayList<TaskDef>();
        List<TaskDef> taskProcessEntities = new ArrayList<TaskDef>();

        List<String> processes_names = (List<String>) execution.getVariable("processes_names");

        for (String process_name : processes_names)
        {
            processEntities.add(processDefBusinessService.retrieveProcessByName(process_name));
        }

        for (ProcessDef process : processEntities)
        {
            taskProcessEntities = taskDefBusinessService.retrieveAll(process.getProcessId());
            for (TaskDef task:taskProcessEntities)
            {
                taskEntities.add(task);
            }
        }

        LOGGER.info("The following "+ taskEntities.size()+ " Tasks exist in database: ");

        Map<String, String> tasks = new HashMap<String, String>();
        for (TaskDef task : taskEntities) {
            LOGGER.info("-- Task with id " + task.getTaskId() + " and Name '" + task.getTaskName() + "'.");
            tasks.put(task.getTaskId().toString(), task.getTaskName());
        }

        execution.setVariable("tasks", tasks);

        return tasks;


    }

}
