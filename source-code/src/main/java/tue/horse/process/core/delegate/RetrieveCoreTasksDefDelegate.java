package tue.horse.process.core.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import tue.horse.business.ProcessDefBusinessService;
import tue.horse.business.TaskDefBusinessService;
import tue.horse.process.core.accessor.CoreDataAccessor;
import tue.horse.process.common.delegate.RetrieveTasksDefDelegate;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;


/**
 * Created by ktraganos on 24-3-2017.
 */
@Named
public class RetrieveCoreTasksDefDelegate implements JavaDelegate{

    private final Logger LOGGER = Logger.getLogger(RetrieveCoreTasksDefDelegate.class.getName());

    @Inject
    private TaskDefBusinessService TaskDefBusinessService;

    @Inject
    private ProcessDefBusinessService ProcessDefBusinessService;

    @Inject
    private CoreDataAccessor coreDataAccessor;


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        RetrieveTasksDefDelegate retrieveTasksDefDelegate = new RetrieveTasksDefDelegate();
        Map<String, String> tasks = new HashMap<String, String>();
        tasks= retrieveTasksDefDelegate.execute(execution, ProcessDefBusinessService, TaskDefBusinessService);
        retrieveTasksDefDelegate = null;

        coreDataAccessor.setTasks(tasks);


    }

}
