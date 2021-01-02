package tue.horse.process.common.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.io.Serializable;

import tue.horse.integration.message.MPMSWebSocketConnector;
import tue.horse.integration.message.WebSocketClientEndpoint;
import tue.horse.process.common.accessor.CommonDataAccessor;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ktraganos on 24-3-2017.
 */
@Named
public class OpenWebSocketDelegate implements JavaDelegate {

    @Inject
    private CommonDataAccessor commonDataAccessor;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String webSocketAddress = commonDataAccessor.getWebsocketAddress();
        //execution.setVariable("senderID","heg/global_execution/production_execution_control");
        String node = execution.getVariable("senderID").toString();
        commonDataAccessor.setAgentNode(execution.getVariable("senderID").toString());

        MPMSWebSocketConnector MPMSWebSocketConnector = new MPMSWebSocketConnector();
        //final WebSocketClientEndpoint clientEndPoint = MPMSWebSocketConnector.main(node, "");
        MPMSWebSocketConnector.main(webSocketAddress, node);

        //execution.setVariable("wsendpoint", clientEndPoint);
        //execution.setVariable("wsendpointToString",clientEndPoint.toString());
        execution.setVariable("wsEndpointSession",WebSocketClientEndpoint.instance.getSession().toString());
        execution.setVariable("wsEndpointSessionID",WebSocketClientEndpoint.instance.getSession().getId().toString());


    }
}
