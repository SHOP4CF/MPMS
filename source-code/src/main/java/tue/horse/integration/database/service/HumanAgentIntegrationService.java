package tue.horse.integration.database.service;

import tue.horse.entity.HumanAgent;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class HumanAgentIntegrationService {

    @Inject
    private EntityManager entityManager;

    /**
     * Persist the given human agent entity.
     *
     * @param humanAgentEntity new human agent entity
     * @return persisted work order id
     */
    public Long persist(HumanAgent humanAgentEntity) {
        if (humanAgentEntity == null) {
            throw new IllegalArgumentException("humanAgentEntity must not be null");
        }
        entityManager.persist(humanAgentEntity);
        return humanAgentEntity.getHumanAgentId();
    }



    /**
     * Search for a human agent entity with the given id.
     *
     * @param humanAgentId work order id
     * @return found work order or null if no one exist
     */
    public HumanAgent find(Long humanAgentId) {
        return entityManager.find(HumanAgent.class, humanAgentId);
    }





    /**
     * Update a human agent entity.
     *
     * @param humanAgentEntity entity
     * @return refreshed work order
     */
    public HumanAgent update(HumanAgent humanAgentEntity) {
        if (humanAgentEntity == null) {
            throw new IllegalArgumentException("humanAgentEntity must not be null");
        }
        return entityManager.merge(humanAgentEntity);
    }



    /**
     * List all human agents of a given team
     *
     * @param human_agent_team_id
     * @return list of human agent entities of a given team
     */
    public List<HumanAgent> list(Long human_agent_team_id) {
        Query query = entityManager.createQuery("SELECT h FROM HumanAgent h WHERE h.human_agent_team_id= :human_agent_team_id")
                .setParameter("human_agent_team_id",human_agent_team_id);
        return query.getResultList();
    }



    /**
     * List all available human agents of a given role
     *
     * @param human_agent_role_id
     * @return list of all available human agents of a given role_id
     */
    public List<HumanAgent> findAvailableHumanAgentByRole(Long human_agent_role_id) {
        Query query = entityManager.createQuery("SELECT h FROM HumanAgent h WHERE h.human_agent_operation_status = :human_agent_operation_status AND h.human_agent_role_id = :human_agent_role_id")
                .setParameter("human_agent_operation_status","Available")
                .setParameter("human_agent_role_id",human_agent_role_id);
        return query.getResultList();
    }
}
