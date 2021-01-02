package tue.horse.integration.message;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import tue.horse.process.common.accessor.CommonDataAccessor;
import tue.horse.integration.message.MPMSWebSocketConnector;
import tue.horse.integration.message.WebSocketClientEndpoint;
import tue.horse.utils.StringUtils;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonArray;
import javax.json.JsonObject;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ktraganos on 13-6-2017.
 */
public class SendMessageToMessageBus {

    public static SendMessageToMessageBus instance;

    @Inject
    private CommonDataAccessor commonDataAccessor;

    public void send(DelegateExecution execution, String message) {

        WebSocketClientEndpoint clientEndpoint = WebSocketClientEndpoint.instance;

        clientEndpoint.sendMessage(message);
        clientEndpoint = null;

        execution.setVariable(execution.getCurrentActivityId().toString(),message);
    }
}
