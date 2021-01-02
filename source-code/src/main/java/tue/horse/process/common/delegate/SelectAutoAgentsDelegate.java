package tue.horse.process.common.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import tue.horse.business.AgentBusinessService;
import tue.horse.business.RoleBusinessService;
import tue.horse.entity.Agent;
import tue.horse.entity.Role;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


/**
 * Created by ktraganos on 10-7-2018
 */
@Named
public class SelectAutoAgentsDelegate implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(SelectAutoAgentsDelegate.class.getName());

    @Inject
    private RoleBusinessService RoleBusinessService;

    @Inject
    private AgentBusinessService AgentBusinessService;


    @Override
    public void execute(DelegateExecution execution) throws Exception {

        LOGGER.info("Delegate code for Selecting Auto Agents is being executed..");

        //Query the database to select the best agent(s)



//        List<String> processes_names = (List<String>) execution.getVariable("processes_names");
//        Role roleEntity = new Role();
//
//        //Core processes
//        if (processes_names.contains("Core Process"))
//        {
//            //Select role A agent
//            roleEntity = RoleBusinessService.retrieveRoleByName("role_A");
//
//            List<Agent> availableRoleAAgents = AgentBusinessService.retrieveAvailableAgentByRoleId(roleEntity.getRoleId());
//            for (Agent availableRoleAAgent : availableRoleAAgents)
//            {
//                execution.setVariable("roleA_AgentID", availableRoleAAgent.getAgentId());
//                LOGGER.info("The following available role A agents exists: " + availableRoleAAgent.getAgentName());
//                break;
//            }
//
//
//            //Select role B agent
//            roleEntity = RoleBusinessService.retrieveRoleByName("role_B");
//
//            List<Agent> availableRoleBAgents = AgentBusinessService.retrieveAvailableAgentByRoleId(roleEntity.getRoleId());
//            for (Agent availableRoleBAgent : availableRoleBAgents)
//            {
//                execution.setVariable("roleB_AgentID", availableRoleBAgent.getAgentId());
//                LOGGER.info("The following available role B agents exists: " + availableRoleBAgent.getAgentName());
//                break;
//            }
//        }
//
//        //Subprocess
//        else if (processes_names.contains("Subprocess"))
//        {
//
//        }


    }

}

