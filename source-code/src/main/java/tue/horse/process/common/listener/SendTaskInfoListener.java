package tue.horse.process.common.listener;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import tue.horse.integration.message.ComposeTaskMessage;
import tue.horse.integration.message.SendMessageToMessageBus;

import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonValue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by ktraganos on 22-3-2017.
 */
@Named
public class SendTaskInfoListener implements TaskListener {

    private final static Logger LOGGER = Logger.getLogger(SendTaskInfoListener.class.getName());

    @Override
    public void notify(DelegateTask delegateTask) {

        String taskId = "";
        String taskInstanceId = delegateTask.getId();
        String taskName = delegateTask.getName();
        JsonValue jsonBody = JsonValue.NULL;

        LOGGER.info("Task Name: " + taskName);

        Map<String, String> tasks = (Map) delegateTask.getExecution().getVariable("tasks");

        for (Object o : tasks.keySet()) {
            if (tasks.get(o).equals(taskName)) {
                taskId = o.toString();
            }
        }

        String autoAgentsIDs = "";
        if (taskName.equals("'Task B' by Robot"))
        {
//          autoAgentsIDs = delegateTask.getExecution().getVariable("roleA_AgentID").toString() + "," + delegateTask.getExecution().getVariable("roleB_AgentID").toString();

            jsonBody = Json.createObjectBuilder()
                    .add("op", "call_service")
                    .add("service", "/task_request")
                    .add("args", Json.createObjectBuilder()
                            .add("agent_ids", autoAgentsIDs)
                            .add("task_id", taskId)
                            .add("task_instance_id", taskInstanceId)
                            .add("process_instance_id", delegateTask.getExecution().getProcessInstanceId().toString())
                            .add("product_no", delegateTask.getExecution().getVariable("productNo").toString()).build()).build();

        }


        ComposeTaskMessage composeTaskMessage = ComposeTaskMessage.instance;
        String message = composeTaskMessage.compose(delegateTask.getExecution(),taskName,taskInstanceId,autoAgentsIDs, "HTS", jsonBody);

        //System.out.println(message);

        //SendMessageToMessageBus sendMessageToMessageBus = new SendMessageToMessageBus();
        SendMessageToMessageBus sendMessageToMessageBus = SendMessageToMessageBus.instance;

        //sendMessageToMessageBus.send(delegateTask.getExecution(), message);

    }
}
