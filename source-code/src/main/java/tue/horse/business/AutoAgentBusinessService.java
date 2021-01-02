package tue.horse.business;

import tue.horse.entity.AutoAgent;
import tue.horse.integration.database.service.AutoAgentIntegrationService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class AutoAgentBusinessService {

    @Inject
    private AutoAgentIntegrationService AutoAgentIntegrationService;


    /**
     * Save the given auto agent entity.
     *
     * @param autoAgentEntity new auto agent entity
     * @return persisted work order id
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Long save(AutoAgent autoAgentEntity) {
        return AutoAgentIntegrationService.persist(autoAgentEntity);
    }



    /**
     * Retrieve a auto agent entity with the given id.
     *
     * @param auto_agent_id work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public AutoAgent retrieve(Long auto_agent_id) {
        return AutoAgentIntegrationService.find(auto_agent_id);
    }



    /**
     * Update a auto agent entity.
     *
     * @param autoAgentEntity auto agent entity
     * @return refreshed work order
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public AutoAgent update(AutoAgent autoAgentEntity ) {
        return AutoAgentIntegrationService.update(autoAgentEntity);
    }


    /**
     * Retrieve available auto agents entity with the given role id
     *
     * @param role_id work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<AutoAgent> retrieveAvailableAutoAgentByRoleId(Long role_id) {
        return AutoAgentIntegrationService.findAvailableAutoAgentByRole(role_id);
    }


    /**
     * Retrieve all auto agent entities of a given team.
     *
     * @return list of auto agent entities of a given team
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<AutoAgent> retrieveAllFromTeam(Long auto_agent_team_id) {
        return AutoAgentIntegrationService.list(auto_agent_team_id);
    }

}
