package tue.horse.process.common.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Map.Entry;
import java.net.*;

import tue.horse.integration.message.ComposeTaskMessage;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;


import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ktraganos on 22-3-2017.
 */
@Named
public class GetTaskListener implements TaskListener {

    private final static Logger LOGGER = Logger.getLogger(GetTaskListener.class.getName());

    @Override
    public void notify(DelegateTask delegateTask) {

        String taskId = delegateTask.getId();
        String taskName = delegateTask.getName();

        LOGGER.info("Task Name: " + taskName);

        Map<String, String> tasks = (Map) delegateTask.getExecution().getVariable("tasks");

//        tasks.entrySet().stream().filter(e -> e.getValue().equals("g")).map(e -> e.getKey()).findFirst();

        for (Object o : tasks.keySet()) {
            if (tasks.get(o).equals(taskName)) {
                delegateTask.getExecution().setVariable("taskID",o.toString());
                delegateTask.getExecution().setVariable("taskInstanceID",taskId);
            }
        }
        //delegateTask.getExecution().setVariable(TestMainDataAccessor.EVALUATEDEFECTDETECTED_TASKID, taskId);

        ComposeTaskMessage composeTaskMessage = ComposeTaskMessage.instance;
        JsonValue jsonBody;
        jsonBody = Json.createObjectBuilder()
                .add("op", "call_service")
                .add("service", "/task_request")
                .add("args", Json.createObjectBuilder()
                        .add("agent_ids", "")
                        .add("task_id", taskId)
                        .add("task_instance_id", "")
                        .add("process_instance_id", delegateTask.getExecution().getProcessInstanceId().toString()).build()).build();

        String message = composeTaskMessage.compose(delegateTask.getExecution(),delegateTask.getName(),delegateTask.getId(), "", "HTS", jsonBody);

    }
}
