package tue.horse.integration.message;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import tue.horse.integration.rest.RESTCalls;
import tue.horse.process.common.accessor.CommonDataAccessor;
import tue.horse.process.common.delegate.TriggerIncomingEventDelegate;
import tue.horse.utils.JsonUtils;

import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by ktraganos on 5-4-2017.
 */
@Named
public class ReceiveMessageFromMessageBus {

    @Inject
    private RuntimeService runtimeService;

    @Inject
    private CommonDataAccessor commonDataAccessor;


    @Inject
    private RESTCalls restCalls;

    private final Logger LOGGER = Logger.getLogger(ReceiveMessageFromMessageBus.class.getName());

    public void execute(String message) {
        String parent_process_instance_id = "";
        String process_instance_id = "";
        String topic = "";
        String type = "";
        String subtype = "";
        String catch_event_message = "";
        String task_name = "";
        String eventId = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String timestamp  = dateFormat.format(new Date());

        try
        {
            JsonObject jsonReceivedMessage;
            JsonObject jsonBody;
            JsonObject jsonDetails;
            JsonObject jsonVariables;
            System.out.println(timestamp + " Received ws message:");
            System.out.println(message);
            if (JsonUtils.isJSONValid(message)) {
                jsonReceivedMessage = Json.createReader(new StringReader(message)).readObject();

                topic = jsonReceivedMessage.getString("Topic");
                System.out.println(topic);

                type = jsonReceivedMessage.getString("Type");
                System.out.println(type);

                subtype = jsonReceivedMessage.getString("Subtype");
                System.out.println(subtype);

                if (!jsonReceivedMessage.getString("Receivers").equals("*"))
                {
                    //String[] Receiver = jsonReceivedMessage.getString("Receivers").split(Pattern.quote("/"));
                    String[] Receiver = jsonReceivedMessage.getString("Receivers").split("/");
                    parent_process_instance_id=Receiver[Receiver.length-1];
                    System.out.println("Parent process_instance_id: " + parent_process_instance_id);
//                    parent_process_instance_id=Receiver[Recseiver.length-1];
//                    System.out.println(parent_process_instance_id);
                }

                jsonBody = jsonReceivedMessage.getJsonObject("Body");


                RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
                Map<String, String> tasks = new HashMap<String, String>();

                switch (Integer.parseInt(type)){
                    //debug
                    case 1: break;

                    //event
                    case 2:
                        jsonDetails = jsonBody.getJsonObject("Details");
                        //System.out.println(jsonValues.getString("task_id"));
                        //System.out.println(jsonPayload.getString("TaskStatus"));
                        process_instance_id = jsonDetails.getString("process_instance_id");
                        System.out.println("the process_instance_id of '"+ topic + "' message is: " + process_instance_id);

                        String task_instance_id = jsonDetails.getString("task_instance_id");
                        System.out.println("the task_instance_id of '"+ topic + "' message is: " + task_instance_id);

                        tasks = (Map<String, String>) runtimeService.getVariable(process_instance_id,"tasks");

                        //System.out.println(tasks.toString());
                        for (Object o : tasks.keySet()) {
                            if (o.toString().equals(jsonDetails.getString("task_id"))) {
                                task_name = tasks.get(o);
                            }
                        }

                        if (topic.equals("task_started")) {

//                            RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
//
//                            Map<String, String> tasks = new HashMap<String, String>();
//                            tasks = (Map<String, String>) runtimeService.getVariable(process_instance_id,"tasks");
//
//                            //System.out.println(tasks.toString());
//                            for (Object o : tasks.keySet()) {
//                                if (o.toString().equals(jsonDetails.getString("task_id"))) {
//                                    task_name = tasks.get(o);
//                                }
//                            }
                            if (task_name.equals("Check WSA visually")) {
                                runtimeService.setVariable(process_instance_id,"pickedupcounter",((Integer) runtimeService.getVariable(process_instance_id,"pickedupcounter"))+1);
                            }
                        }
                        else if (topic.equals("task_completed")) {


                            jsonVariables = jsonBody.getJsonObject("Variables");

                            System.out.println("the variables are " + jsonVariables.toString());

                            RESTCalls restCalls = new RESTCalls();
                            restCalls.TaskComplete(runtimeService.getVariable(process_instance_id,"enginehostaddress").toString(),runtimeService.getVariable(process_instance_id,"enginehostport").toString(), process_instance_id, task_instance_id, jsonVariables.toString());
                            restCalls = null;

//                            if (task_name.equals("Place WSA in box")) {
//                                //runtimeService.setVariable(process_instance_id,"boxAcounter",((Integer) runtimeService.getVariable(process_instance_id,"boxAcounter"))+1);
//                                testMainDataAccessor.setBoxACounter(((Integer) testMainDataAccessor.getBoxACounter())+1);
//                            }

                        }
                        else if (topic.equals("task_cancelled")) {


                            jsonVariables = jsonBody.getJsonObject("Variables");

                            System.out.println("the variables are " + jsonVariables.toString());
                            runtimeService.setVariable(process_instance_id,"task_status", "cancelled");

                            RESTCalls restCalls = new RESTCalls();
                            restCalls.TaskComplete(runtimeService.getVariable(process_instance_id,"enginehostaddress").toString(),runtimeService.getVariable(process_instance_id,"enginehostport").toString(), process_instance_id, task_instance_id, jsonVariables.toString());
                            restCalls = null;

                        }
                        else if (topic.equals("exception")) {

                            eventId = jsonBody.getString("EventID");

                            jsonVariables = jsonBody.getJsonObject("Variables");

                            System.out.println("the variables are " + jsonVariables.toString());
                            TriggerIncomingEventDelegate triggerIncomingEventDelegate = new TriggerIncomingEventDelegate();
                            triggerIncomingEventDelegate.execute(runtimeService, parent_process_instance_id, eventId);
                            //throw new BpmnError("exception_code", subtype);

                        }
                        break;

                    //system
                    case 3: break;

                    //custom
                    case 4:

                        if (topic.equals("task_completed")){

                            jsonDetails = jsonBody.getJsonObject("Details");
                            //System.out.println(jsonValues.getString("task_id"));
                            //System.out.println(jsonPayload.getString("TaskStatus"));
                            process_instance_id = jsonDetails.getString("process_instance_id");
                            System.out.println(process_instance_id);
//                            ReceiveMessageFromTaskDelegate receiveMessageFromTaskDelegate = new ReceiveMessageFromTaskDelegate();
//                            receiveMessageFromTaskDelegate.execute(jsonValues.getString("process_instance_id"), "");

//                            RuntimeService runtimeService = ProcessEngines.getDefaultProcessEngine().getRuntimeService();
//                            //System.out.println(runtimeService.toString());
//
                            tasks = (Map<String, String>) runtimeService.getVariable(process_instance_id,"tasks");

                            //System.out.println(tasks.toString());
                            for (Object o : tasks.keySet()) {
                                if (o.toString().equals(jsonDetails.getString("task_id"))) {
                                    catch_event_message = tasks.get(o);
                                }
                            }

                            MessageCorrelationBuilder correlationBuilder = runtimeService.createMessageCorrelation(catch_event_message);
                            correlationBuilder.processInstanceId(process_instance_id);
                            //correlationBuilder.processInstanceVariableEquals(CommonDataAccessor.UNIQUE_BUSINESS_KEY, commonDataAccessor.getUniqueBusinessKey());
                            //correlationBuilder.setVariable(testMainDataAccessor.BATCH_COUNT, testMainDataAccessor.getBatchCount());
                            correlationBuilder.correlateAllWithResult();

                        }

                        break;

                    default:break;
                }



            }









        } catch (Exception e) {
            //System.err.println("ReceiveMessageFromMessageBus exception: " + e);
            LOGGER.log(Level.WARNING,"Error", e);
        }

    }
}
