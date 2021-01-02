package tue.horse.integration.message;

import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import tue.horse.process.common.accessor.CommonDataAccessor;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ktraganos on 5-4-2017.
 */
@Named
public class ReceiveMessageFromTaskDelegate {


    @Inject
    private RuntimeService runtimeService;

    @Inject
    private CommonDataAccessor commonDataAccessor;



    private final Logger LOGGER = Logger.getLogger(ReceiveMessageFromTaskDelegate.class.getName());


    public void execute(String processInstanceID, String taskInstanceID) {

        try
        {


        } catch (Exception e) {
            //System.err.println("ReceiveMessageFromTaskDelegate exception: " + e);
            LOGGER.log(Level.WARNING,"Error", e);
        }

    }
}