package tue.horse.process.common.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.context.Context;
import tue.horse.process.common.accessor.CommonDataAccessor;
import tue.horse.process.core.accessor.CoreDataAccessor;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ktraganos on 8-3-2017.
 */
@Named
public class TaskAssignmentEmailListener implements TaskListener {

    private final static Logger LOGGER = Logger.getLogger(TaskAssignmentEmailListener.class.getName());

    @Inject
    private CommonDataAccessor commonDataAccessor;

    @Inject
    private CoreDataAccessor coreDataAccessor;

    @Override
    public void notify(DelegateTask delegateTask) {
        String HOST = commonDataAccessor.getMailHost();
        Integer PORT = commonDataAccessor.getMailPort();
        String USER = commonDataAccessor.getMailUser();
        String PWD = commonDataAccessor.getMailPassword();

        String assignee = delegateTask.getAssignee();
        coreDataAccessor.setAssigneeRepeatProcessTask(assignee);
        String taskId = delegateTask.getId();
        coreDataAccessor.setTaskIdRepeatProcess(taskId);

        if (assignee != null) {

            // Get User Profile from User Management
            IdentityService identityService = Context.getProcessEngineConfiguration().getIdentityService();
            User user = identityService.createUserQuery().userId(assignee).singleResult();

            if (user != null) {

                // Get Email Address from User Profile
                String recipient = user.getEmail();

                if (recipient != null && !recipient.isEmpty()) {

                    Email email = new SimpleEmail();
                    email.setCharset("utf-8");
                    email.setHostName(HOST);
                    email.setSmtpPort(PORT);
                    email.setSSL(true);
                    email.setTLS(true);
                    //email.setSslSmtpPort("465");
                    email.setAuthentication(USER, PWD);

                    try {
                        email.setFrom(USER);
                        email.setSubject("Task assigned: " + delegateTask.getName());
                        if (commonDataAccessor.getEngineHostName().equals("vmknoll33.informatik.tu-muenchen.de")){
                            email.setMsg("Please complete: http://" + InetAddress.getLocalHost().getHostAddress() + "/camunda/app/tasklist/default/#/task/" + taskId);
                        }
                        else
                        {
                            email.setMsg("Please complete: http://" + InetAddress.getLocalHost().getHostAddress() + ":" + delegateTask.getExecution().getVariable("enginehostport") + "/camunda/app/tasklist/default/#/task/" + taskId);
                        }

                        email.addTo(recipient);

                        email.send();
                        LOGGER.info("Task Assignment Email successfully sent to user '" + assignee + "' with address '" + recipient + "'.");

                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "Could not send email to assignee", e);
                    }

                } else {
                    LOGGER.warning("Not sending email to user " + assignee + "', user has no email address.");
                }

            } else {
                LOGGER.warning("Not sending email to user " + assignee + "', user is not enrolled with identity service.");
            }

        }
    }

}
