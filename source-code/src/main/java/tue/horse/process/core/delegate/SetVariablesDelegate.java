package tue.horse.process.core.delegate;

import org.camunda.bpm.engine.delegate.JavaDelegate;
import tue.horse.integration.message.SendMessageToMessageBus;
import tue.horse.integration.message.ComposeTaskMessage;
import tue.horse.process.common.accessor.CommonDataAccessor;
import tue.horse.process.core.accessor.CoreDataAccessor;

import org.camunda.bpm.engine.delegate.DelegateExecution;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import java.util.Properties;
import java.io.InputStream;

import tue.horse.utils.ConfigPropertiesMain;

/**
 * Created by ktraganos on 6-4-2017
 */
@Named
public class SetVariablesDelegate implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(SetVariablesDelegate.class.getName());

    @Inject
    private CommonDataAccessor commonDataAccessor;

    @Inject
    private CoreDataAccessor coreDataAccessor;

    public void execute(DelegateExecution execution) throws Exception {

        LOGGER.info("Delegate code for Setting Process Variables is being executed..");

        ConfigPropertiesMain configmain = new ConfigPropertiesMain();
        Properties props = new Properties();
        Properties mail_props = new Properties();
        props = configmain.main("config.properties");
        mail_props = configmain.main("mail-config.properties");
        configmain = null;

        //execution.setVariable("webSocketAddress", props.getProperty("webSocketAddress"));

        commonDataAccessor.setWebsocketAddress(props.getProperty("webSocketAddress"));
        commonDataAccessor.setEngineHostAddress(props.getProperty("engineHostAddress"));
        commonDataAccessor.setEngineHostName(props.getProperty("engineHostName"));
        commonDataAccessor.setEngineHostPort(props.getProperty("engineHostPort"));
        commonDataAccessor.setDBUrl(props.getProperty("db_url"));
//        commonDataAccessor.setJDBCDriver(props.getProperty("jdbc_driver"));
//        commonDataAccessor.setDBUsername(props.getProperty("db_username"));
//        commonDataAccessor.setDBPassword(props.getProperty("db_password"));
//        coreDataAccessor.setProductNo("1234");
        commonDataAccessor.setMailHost(mail_props.getProperty("mail.smtp.host"));
        commonDataAccessor.setMailPort(Integer.parseInt(mail_props.getProperty("mail.smtp.port")));
        commonDataAccessor.setMailUser(mail_props.getProperty("mail.user"));
        commonDataAccessor.setMailPassword(mail_props.getProperty("mail.password"));

        commonDataAccessor.setCallerProcessInstanceID(execution.getProcessInstanceId());

        List<String> processes_names = new ArrayList<String>();
        processes_names.add("Core Process");
//        processes_names.add("Sub Process");

        execution.setVariable("processes_names",processes_names);

        execution.setVariable("topic", "task_assigned");
        execution.setVariable("priority", "2");
        execution.setVariable("responseMessageID", "");
        execution.setVariable("receivers", "rosbridge");
        execution.setVariable("messageID", "");
        execution.setVariable("type", "2");
        execution.setVariable("subtype", "notification");
        execution.setVariable("internal", "true");
        execution.setVariable("externalbrokersIDs", "*");
        execution.setVariable("senderBroker", "");

        execution.setVariable("senderID", "heg/global_execution/production_execution_control/" + execution.getProcessInstanceId());

        //Instantiating 'SendMessageToMessageBus' class
        SendMessageToMessageBus sendMessageToMessageBus = new SendMessageToMessageBus();
        SendMessageToMessageBus.instance = sendMessageToMessageBus;

        //Instantiating 'ComposeTaskMessage' class
        ComposeTaskMessage composeTaskMessage = new ComposeTaskMessage();
        ComposeTaskMessage.instance = composeTaskMessage;

    }

}
