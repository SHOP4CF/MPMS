package tue.horse.process.common.accessor;

import org.camunda.bpm.engine.cdi.BusinessProcess;
import javax.inject.Inject;

/**
 * Created by ktraganos on 7-3-2017.
 */
public class CommonDataAccessor {

    @Inject
    private BusinessProcess businessProcess;

    public static final String UNIQUE_BUSINESS_KEY = "uniqueBusinessKey";
    public static final String CALLER_PROCESS_INSTANCE_ID = "callerProcessInstanceId";
    public static final String WEBSOCKET_ADDRESS = "websocketaddress";
    public static final String ENGINE_HOST_ADDRESS = "enginehostaddress";
    public static final String ENGINE_HOST_NAME = "enginehostname";
    public static final String ENGINE_HOST_PORT = "enginehostport";
    public static final String AGENT_NODE="agentNode";
    public static final String DB_URL="db_url";
    public static final String JDBC_DRIVER="jdbc_driver";
    public static final String DB_USERNAME="db_username";
    public static final String DB_PASSWORD="db_password";

    public static final String MAIL_HOST="mail_host";
    public static final String MAIL_PORT="mail_port";
    public static final String MAIL_USER="mail_user";
    public static final String MAIL_PASSWORD="mail_password";

    public String getUniqueBusinessKey() {
        return businessProcess.getVariable(UNIQUE_BUSINESS_KEY);
    }

    public void setUniqueBusinessKey(String uniqueBusinessKey) {
        businessProcess.setVariable(UNIQUE_BUSINESS_KEY, uniqueBusinessKey);
    }


    public String getCallerProcessInstanceID() {
        return businessProcess.getVariable(CALLER_PROCESS_INSTANCE_ID);
    }

    public void setCallerProcessInstanceID(String callerProcessInstanceID) {
        businessProcess.setVariable(CALLER_PROCESS_INSTANCE_ID, callerProcessInstanceID);
    }


    public String getWebsocketAddress() {
        return businessProcess.getVariable(WEBSOCKET_ADDRESS);
    }

    public void setWebsocketAddress(String websocketaddress) {
        businessProcess.setVariable(WEBSOCKET_ADDRESS, websocketaddress);
    }

    public String getEngineHostAddress() {
        return businessProcess.getVariable(ENGINE_HOST_ADDRESS);
    }

    public void setEngineHostAddress(String enginehostaddress) {
        businessProcess.setVariable(ENGINE_HOST_ADDRESS, enginehostaddress);
    }

    public String getEngineHostName() {
        return businessProcess.getVariable(ENGINE_HOST_NAME);
    }

    public void setEngineHostName(String enginehostname) {
        businessProcess.setVariable(ENGINE_HOST_NAME, enginehostname);
    }

    public String getEngineHostPort() {
        return businessProcess.getVariable(ENGINE_HOST_PORT);
    }

    public void setEngineHostPort(String enginehostport) {
        businessProcess.setVariable(ENGINE_HOST_PORT, enginehostport);
    }

    public String getAgentNode() {
        return businessProcess.getVariable(AGENT_NODE);
    }

    public void setAgentNode(String agentNode) {
        businessProcess.setVariable(AGENT_NODE, agentNode);
    }


    public String getDBUrl() {
        return businessProcess.getVariable(DB_URL);
    }

    public void setDBUrl(String dbUrl) {
        businessProcess.setVariable(DB_URL, dbUrl);
    }


    public String getJDBCDriver() {
        return businessProcess.getVariable(JDBC_DRIVER);
    }

    public void setJDBCDriver(String jdbcDriver) {
        businessProcess.setVariable(JDBC_DRIVER, jdbcDriver);
    }


    public String getDBUsername() {
        return businessProcess.getVariable(DB_USERNAME);
    }

    public void setDBUsername(String dbUsername) {
        businessProcess.setVariable(DB_USERNAME, dbUsername);
    }


    public String getDBPassword() {
        return businessProcess.getVariable(DB_PASSWORD);
    }

    public void setDBPassword(String dbPassword) {
        businessProcess.setVariable(DB_PASSWORD, dbPassword);
    }

    public String getMailHost() {
        return businessProcess.getVariable(MAIL_HOST);
    }

    public void setMailHost(String mailHost) {
        businessProcess.setVariable(MAIL_HOST, mailHost);
    }

    public Integer getMailPort() {
        return businessProcess.getVariable(MAIL_PORT);
    }

    public void setMailPort(Integer mailPort) {
        businessProcess.setVariable(MAIL_PORT, mailPort);
    }

    public String getMailUser() {
        return businessProcess.getVariable(MAIL_USER);
    }

    public void setMailUser(String mailUser) {
        businessProcess.setVariable(MAIL_USER, mailUser);
    }

    public String getMailPassword() {
        return businessProcess.getVariable(MAIL_PASSWORD);
    }

    public void setMailPassword(String mailPassword) {
        businessProcess.setVariable(MAIL_PASSWORD, mailPassword);
    }

}