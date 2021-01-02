package tue.horse.business;

import tue.horse.entity.Agent;
import tue.horse.integration.database.service.AgentIntegrationService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class AgentBusinessService {

    @Inject
    private AgentIntegrationService AgentIntegrationService;


    /**
     * Save the given agent entity.
     *
     * @param agentEntity new agent entity
     * @return persisted work order id
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Long save(Agent agentEntity) {
        return AgentIntegrationService.persist(agentEntity);
    }



    /**
     * Retrieve a agent entity with the given id.
     *
     * @param agent_id work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Agent retrieve(Long agent_id) {
        return AgentIntegrationService.find(agent_id);
    }



    /**
     * Update an agent entity.
     *
     * @param agentEntity agent entity
     * @return refreshed work order
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Agent update(Agent agentEntity ) {
        return AgentIntegrationService.update(agentEntity);
    }


    /**
     * Retrieve online agents entity with the given role id
     *
     * @param role_id work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<Agent> retrieveOnlineAgentByRoleId(Long role_id) {
        return AgentIntegrationService.findOnlineAgentByRole(role_id);
    }


    /**
     * Retrieve online agent entity with the given name
     *
     * @param agent_name
     * @return found agent or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<Agent> retrieveOnlineAgentByName(String agent_name) {
        return AgentIntegrationService.findOnlineAgentByName(agent_name);
    }


}
