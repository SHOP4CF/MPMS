package tue.horse.business;

import tue.horse.entity.HumanAgent;
import tue.horse.integration.database.service.HumanAgentIntegrationService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class HumanAgentBusinessService {

    @Inject
    private HumanAgentIntegrationService HumanAgentIntegrationService;


    /**
     * Save the given human agent entity.
     *
     * @param humanAgentEntity new human agent entity
     * @return persisted work order id
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Long save(HumanAgent humanAgentEntity) {
        return HumanAgentIntegrationService.persist(humanAgentEntity);
    }



    /**
     * Retrieve a human agent entity with the given id.
     *
     * @param human_agent_id work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public HumanAgent retrieve(Long human_agent_id) {
        return HumanAgentIntegrationService.find(human_agent_id);
    }


    /**
     * Retrieve an available human agent entity with the given role id
     *
     * @param role_id work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<HumanAgent> retrieveAvailableHumanAgentByRoleId(Long role_id) {
        return HumanAgentIntegrationService.findAvailableHumanAgentByRole(role_id);
    }


    /**
     * Update a human agent entity.
     *
     * @param humanAgentEntity human agent entity
     * @return refreshed work order
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public HumanAgent update(HumanAgent humanAgentEntity ) {
        return HumanAgentIntegrationService.update(humanAgentEntity);
    }



    /**
     * Retrieve all human agent entities of a given team.
     *
     * @return list of human agent entities of a given team
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<HumanAgent> retrieveAllFromTeam(Long human_agent_team_id) {
        return HumanAgentIntegrationService.list(human_agent_team_id);
    }

}
