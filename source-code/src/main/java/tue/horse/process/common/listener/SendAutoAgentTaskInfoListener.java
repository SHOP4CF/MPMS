package tue.horse.process.common.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import tue.horse.integration.message.ComposeTaskMessage;
import tue.horse.integration.message.SendMessageToMessageBus;

import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonValue;
import org.camunda.spin.json.SpinJsonNode;
import java.util.Map;
import java.util.logging.Logger;

import static org.camunda.spin.DataFormats.json;
import static org.camunda.spin.Spin.S;


/**
 * Created by ktraganos on 22-3-2017.
 */
@Named
public class SendAutoAgentTaskInfoListener implements TaskListener {

    private final static Logger LOGGER = Logger.getLogger(SendAutoAgentTaskInfoListener.class.getName());

    @Override
    public void notify(DelegateTask delegateTask) {

        String taskId = "";
        String taskInstanceId = delegateTask.getId();
        String taskName = delegateTask.getName();

        LOGGER.info("Task Name: " + taskName);

        Map<String, String> tasks = (Map) delegateTask.getExecution().getVariable("tasks");

        for (Object o : tasks.keySet()) {
            if (tasks.get(o).equals(taskName)) {
                taskId = o.toString();
            }
        }

        String autoAgentsIDs = "";
        //        autoAgentsIDs = delegateTask.getExecution().getVariable("roleA_AgentID").toString();

        String productNo = delegateTask.getExecution().getVariable("productNo").toString();

        ComposeTaskMessage composeTaskMessage = ComposeTaskMessage.instance;

        JsonValue jsonBody;
        jsonBody = Json.createObjectBuilder()
                .add("agent_ids", autoAgentsIDs)
                .add("task_id", taskId)
                .add("task_instance_id", taskInstanceId)
                .add("process_instance_id", delegateTask.getExecution().getProcessInstanceId().toString())
                .add("product_no", productNo)
                .add("workcell_id", "1").build();


        String message = composeTaskMessage.compose(delegateTask.getExecution(),taskName,taskInstanceId,autoAgentsIDs, "AutoAgent", jsonBody);

        //System.out.println(message);

        //SendMessageToMessageBus sendMessageToMessageBus = new SendMessageToMessageBus();
        SendMessageToMessageBus sendMessageToMessageBus = SendMessageToMessageBus.instance;

        //sendMessageToMessageBus.send(delegateTask.getExecution(), message);

    }
}
