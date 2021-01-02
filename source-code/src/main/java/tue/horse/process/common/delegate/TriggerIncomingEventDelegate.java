package tue.horse.process.common.delegate;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import tue.horse.process.common.accessor.CommonDataAccessor;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
public class TriggerIncomingEventDelegate {

//    @Inject
//    private RuntimeService runtimeService;


    private final Logger LOGGER = Logger.getLogger(TriggerIncomingEventDelegate.class.getName());


    public void execute(RuntimeService runtimeService, String processInstanceId, String eventId)
    {

        try
        {



        } catch (Exception e) {
            LOGGER.log(Level.WARNING,"Error", e);
        }

    }
}
