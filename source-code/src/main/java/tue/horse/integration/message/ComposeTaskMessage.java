package tue.horse.integration.message;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import tue.horse.process.common.accessor.CommonDataAccessor;

import javax.inject.Inject;

import javax.json.Json;
import javax.json.JsonValue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by ktraganos on 13-6-2017.
 */
public class ComposeTaskMessage {

    public static ComposeTaskMessage instance;

    public String message = "";

    @Inject
    private CommonDataAccessor commonDataAccessor;

    /**
     * Composes the websocket message.
     *
     * @param receiverType to whom the message is sent: "HTS" (Hybrid Task Supervisor) / "AR" (Augmented Reality) / "COBOT" / "AutoAgent"
     *
     */
    public String compose(DelegateExecution execution, String taskName, String taskInstanceId, String autoAgentIDs, String receiverType, JsonValue jsonBody) {

        String taskId = "";


        Map<String, String> tasks = (Map) execution.getVariable("tasks");

//        tasks.entrySet().stream().filter(e -> e.getValue().equals("g")).map(e -> e.getKey()).findFirst();

        for (Object o : tasks.keySet()) {
            if (tasks.get(o).equals(taskName)) {
                //delegateTask.getExecution().setVariable("taskID",o.toString());
                //delegateTask.getExecution().setVariable("taskInstanceID",taskId);
                taskId = o.toString();
            }
        }


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMddHHmmss");
        String timestamp  = dateFormat.format(new Date());

        //commonDataAccessor.setCallerProcessInstanceID(execution.getProcessInstanceId());

        switch (receiverType) {
            case "HTS":
                message = Json.createObjectBuilder()
                        .add("Topic", execution.getVariable("topic").toString())
                        .add("Priority", execution.getVariable("priority").toString())
                        .add("SenderID", execution.getVariable("senderID").toString())
                        .add("Receivers", "rosbridge")
                        .add("Type", execution.getVariable("type").toString())
                        .add("Subtype", execution.getVariable("subtype").toString())
                        .add("Timestamp", timestamp)
                        .add("MessageID", timestamp)
                        .add("ResponseMessageID", execution.getVariable("responseMessageID").toString())
                        .add("Internal", execution.getVariable("internal").toString())
                        .add("ExternalBrokers", execution.getVariable("externalbrokersIDs").toString())
                        .add("SenderBroker", execution.getVariable("senderBroker").toString())
                        .add("Body", jsonBody)
//                      .add("Body", Json.createObjectBuilder()
//                              .add("op", "call_service")
//                              .add("service", "/task_request")
//                              .add("args", Json.createObjectBuilder()
//                                      .add("agent_ids", autoAgentIDs)
//                                      .add("task_id", taskId)
//                                      .add("task_instance_id", taskInstanceId)
//                                      .add("process_instance_id", execution.getProcessInstanceId().toString()).build()).build())
                        .build().toString();
                break;
            case "AR":
                message = Json.createObjectBuilder()
                        .add("Topic", execution.getVariable("topic").toString())
                        .add("Priority", execution.getVariable("priority").toString())
                        .add("SenderID", execution.getVariable("senderID").toString())
                        .add("Receivers", "hel/local_execution/humagent_step_execution")
                        .add("Type", execution.getVariable("type").toString())
                        .add("Subtype", execution.getVariable("subtype").toString())
                        .add("Timestamp", timestamp)
                        .add("MessageID", timestamp)
                        .add("ResponseMessageID", execution.getVariable("responseMessageID").toString())
                        .add("Internal", execution.getVariable("internal").toString())
                        .add("ExternalBrokers", execution.getVariable("externalbrokersIDs").toString())
                        .add("SenderBroker", execution.getVariable("senderBroker").toString())
                        .add("Body", jsonBody)
//                      .add("Body", Json.createObjectBuilder()
//                              .add("agent_ids", autoAgentIDs)
//                              .add("task_id", taskId)
//                              .add("task_instance_id", taskInstanceId)
//                              .add("process_instance_id", execution.getProcessInstanceId().toString()).build())
                        .build().toString();
                break;
            case "COBOT":
                message = Json.createObjectBuilder()
                        .add("Topic", execution.getVariable("topic").toString())
                        .add("Priority", execution.getVariable("priority").toString())
                        .add("SenderID", execution.getVariable("senderID").toString())
                        .add("Receivers", "global.automatedagent.mobilebase.mobile_base")
                        .add("Type", execution.getVariable("type").toString())
                        .add("Subtype", execution.getVariable("subtype").toString())
                        .add("Timestamp", timestamp)
                        .add("MessageID", timestamp)
                        .add("ResponseMessageID", execution.getVariable("responseMessageID").toString())
                        .add("Internal", execution.getVariable("internal").toString())
                        .add("ExternalBrokers", execution.getVariable("externalbrokersIDs").toString())
                        .add("SenderBroker", execution.getVariable("senderBroker").toString())
                        .add("Body", jsonBody)
                        .build().toString();
                break;
            case "AutoAgent":
                message = Json.createObjectBuilder()
                        .add("Topic", execution.getVariable("topic").toString())
                        .add("Priority", execution.getVariable("priority").toString())
                        .add("SenderID", execution.getVariable("senderID").toString())
                        .add("Receivers", "KMR_Agent")
                        .add("Type", execution.getVariable("type").toString())
                        .add("Subtype", execution.getVariable("subtype").toString())
                        .add("Timestamp", timestamp)
                        .add("MessageID", timestamp)
                        .add("ResponseMessageID", execution.getVariable("responseMessageID").toString())
                        .add("Internal", execution.getVariable("internal").toString())
                        .add("ExternalBrokers", execution.getVariable("externalbrokersIDs").toString())
                        .add("SenderBroker", execution.getVariable("senderBroker").toString())
                        .add("Body", jsonBody)
                        .build().toString();
                break;
            default:
                throw new IllegalArgumentException("Invalid Receiver Type: " + receiverType);
        }

        System.out.println(message);

        return message;

    }
}
