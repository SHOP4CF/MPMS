package tue.horse.process.common.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import tue.horse.process.common.accessor.CommonDataAccessor;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ktraganos on 24-3-2017.
 */
@Named
public class SetNodeDelegate implements JavaDelegate {

    @Inject
    private CommonDataAccessor commonDataAccessor;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        commonDataAccessor.setCallerProcessInstanceID(execution.getProcessInstanceId());

        execution.setVariable("senderID", "heg/global_execution/production_execution_control/" + execution.getProcessInstanceId());

    }
}