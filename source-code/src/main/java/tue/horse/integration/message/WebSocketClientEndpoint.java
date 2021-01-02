package tue.horse.integration.message;

import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import java.io.OutputStream.*;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

import java.io.IOException;


/**
 * Created by ktraganos on 24-3-2017.
 */
@ClientEndpoint
public class WebSocketClientEndpoint {

    public static WebSocketClientEndpoint instance;
    Session userSession = null;
    private MessageHandler messageHandler;

    public static Map<String, Session> map = new HashMap<String, Session>();
    public static Map<String, Session> peers = Collections.synchronizedMap(map);

    public WebSocketClientEndpoint(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Callback hook for Connection open events.
     *
     * @param userSession the userSession which is opened.
     */
    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket: " + userSession.getId());
        this.userSession = userSession;
        peers.put(userSession.getId(), userSession);
    }


    /**
     * Callback hook for Connection close events.
     *
     * @param userSession the userSession which is getting closed.
     * @param reason the reason for connection close
     */
    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket session " + this.userSession.toString());
        peers.remove(userSession.getId());
        this.userSession = null;
    }


    /**
     * Callback hook for Message Events. This method will be invoked when a client send a message.
     *
     * @param message The text message
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println ("WebSocket received message: " + message);
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }


    /**
     * register message handler
     *
     * @param msgHandler
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }

    public Session getSession() {
        return this.userSession;
    }


    public Session getSessionById(String sessionID) {
        if (peers.containsKey(sessionID))
        {
            System.out.println ("reach here " + sessionID);
            return peers.get(sessionID);
        }
        return this.userSession;
    }

    public Session getSessions(String sessionID) {
//        Set<Session> OpenSessions = this.userSession.getOpenSessions();
//        Iterator iterator = OpenSessions.iterator();
//        while (iterator.hasNext()) {
//            System.out.println ("reach here ");
//            System.out.println ("current session here " + iterator.next().toString());}

        for (Session session : this.userSession.getOpenSessions()) {
            System.out.println ("reach here " + session.getId());
            if (sessionID.equals(session.getId())) {
                return session;
            }
        }
        return null;
        //return OpenSessions;
    }


    /**
     * Send a message.
     *
     * @param message
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }


    /**
     * Message handler
     */
    public static interface MessageHandler {
        public void handleMessage(String message);
    }


    public static byte[] wsClientEndpointToByte(WebSocketClientEndpoint webSocketClientEndpoint ) throws IOException {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objStream = new ObjectOutputStream(byteStream);
        objStream .writeObject(webSocketClientEndpoint);

        return byteStream .toByteArray();
    }

    public static Object byteTowsClientEndpoint(byte[] bytes) throws IOException,ClassNotFoundException {

        ByteArrayInputStream byteStream  = new ByteArrayInputStream(bytes);
        ObjectInputStream objStream  = new ObjectInputStream(byteStream);

        return (WebSocketClientEndpoint) objStream.readObject();
    }

}
