package tue.horse.process.common.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import tue.horse.integration.message.MPMSWebSocketConnector;
import tue.horse.integration.message.WebSocketClientEndpoint;
import tue.horse.process.common.accessor.CommonDataAccessor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.websocket.Session;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ktraganos on 24-3-2017.
 */
@Named
public class CloseWebSocketDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Session session = WebSocketClientEndpoint.instance.getSessionById(execution.getVariable("wsEndpointSessionID").toString());
        if (session != null)
        {
            System.out.println(session.getId());
            session.close();
//              WebSocketClientEndpoint.instance = null;
        }
//        WebSocketClientEndpoint.instance.getSession().close();
//        WebSocketClientEndpoint.instance = null;

    }
}
