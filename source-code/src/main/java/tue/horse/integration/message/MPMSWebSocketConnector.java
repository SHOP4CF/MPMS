package tue.horse.integration.message;

import tue.horse.utils.JsonUtils;

import java.net.URI;
import java.net.URISyntaxException;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by ktraganos on 24-3-2017.
 */

public class MPMSWebSocketConnector {

    private final Logger LOGGER = Logger.getLogger(MPMSWebSocketConnector.class.getName());

    public static void main(String webSocketAddress, String clientnode) {
        WebSocketClientEndpoint clientEndPoint = null;


        try {

            // open websocket
            clientEndPoint = new WebSocketClientEndpoint(new URI(webSocketAddress));
            WebSocketClientEndpoint.instance = clientEndPoint;
            System.out.println("web socket opened");

            // add listener
            clientEndPoint.addMessageHandler(new WebSocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {

                    //System.out.println(message);

                    ReceiveMessageFromMessageBus receiveMessageFromMessageBus = new ReceiveMessageFromMessageBus();
                    receiveMessageFromMessageBus.execute(message);
                }
            });

            System.out.println("ready to register client");

            // send message to websocket
            clientEndPoint.sendMessage("___CONTROL___{'ID':'" + clientnode + "','Operation':'connect'}");

            // wait 5 seconds for messages from websocket
            // Thread.sleep(5000);

            System.out.println("client '" + clientnode + "' registered");
//            System.out.println("ready to send message " + jsonMessage);
//
            // send message to websocket
//          clientEndPoint.sendMessage(jsonMessage);

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

            // close websocket
            //clientEndPoint.onClose(clientEndPoint.userSession,);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }

        //return clientEndPoint;
    }

}