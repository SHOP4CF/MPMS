package tue.horse.process.common.delegate;

import tue.horse.process.common.accessor.CommonDataAccessor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

/**
 * Created by ktraganos on 7-3-2017.
 */
@Named
public class UniqueBusinessKeyDelegate implements JavaDelegate {

    @Inject
    private CommonDataAccessor commonDataAccessor;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        commonDataAccessor.setUniqueBusinessKey(UUID.randomUUID().toString());
        commonDataAccessor.setCallerProcessInstanceID(execution.getProcessInstanceId());
    }
}