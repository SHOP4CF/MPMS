package tue.horse.process.common.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import tue.horse.integration.message.*;
import tue.horse.process.common.accessor.CommonDataAccessor;
import tue.horse.utils.StringUtils;

import javax.json.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ktraganos on 24-3-2017.
 */
@Named
public class SendMessageToWebSocketDelegate implements JavaDelegate {

    @Inject
    private CommonDataAccessor commonDataAccessor;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        System.out.println("The Send service task is named: " + execution.getCurrentActivityName());

        ComposeTaskMessage composeTaskMessage = ComposeTaskMessage.instance;
        JsonValue jsonBody;
        jsonBody = Json.createObjectBuilder()
                .add("op", "call_service")
                .add("service", "/task_request")
                .add("args", Json.createObjectBuilder()
                        .add("agent_ids", "")
                        .add("task_id", "")
                        .add("task_instance_id", "")
                        .add("process_instance_id", execution.getProcessInstanceId().toString()).build()).build();

        String message = composeTaskMessage.compose(execution,execution.getCurrentActivityName(),"", "", "HTS", jsonBody);
        composeTaskMessage = null;

        SendMessageToMessageBus sendMessageToMessageBus = SendMessageToMessageBus.instance;
        sendMessageToMessageBus.send(execution, message);
        sendMessageToMessageBus = null;

        execution.setVariable(execution.getCurrentActivityId().toString(),message);
    }
}
