package tue.horse.integration.database.service;

import tue.horse.entity.AutoAgent;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class AutoAgentIntegrationService {

    @Inject
    private EntityManager entityManager;

    /**
     * Persist the given auto agent entity.
     *
     * @param autoAgentEntity new auto agent entity
     * @return persisted work order id
     */
    public Long persist(AutoAgent autoAgentEntity) {
        if (autoAgentEntity == null) {
            throw new IllegalArgumentException("autoAgentEntity must not be null");
        }
        entityManager.persist(autoAgentEntity);
        return autoAgentEntity.getAutoAgentId();
    }



    /**
     * Search for a auto agent entity with the given id.
     *
     * @param autoAgentId work order id
     * @return found work order or null if no one exist
     */
    public AutoAgent find(Long autoAgentId) {
        return entityManager.find(AutoAgent.class, autoAgentId);
    }





    /**
     * Update a auto agent entity.
     *
     * @param autoAgentEntity entity
     * @return refreshed work order
     */
    public AutoAgent update(AutoAgent autoAgentEntity) {
        if (autoAgentEntity == null) {
            throw new IllegalArgumentException("autoAgentEntity must not be null");
        }
        return entityManager.merge(autoAgentEntity);
    }



    /**
     * List all available auto agents of a given role
     *
     * @param auto_agent_role_id
     * @return list of all available auto agents of a given role_id
     */
    public List<AutoAgent> findAvailableAutoAgentByRole(Long auto_agent_role_id) {
        Query query = entityManager.createQuery("SELECT a FROM AutoAgent a WHERE a.auto_agent_operation_status = :auto_agent_operation_status AND a.auto_agent_role_id = :auto_agent_role_id")
                .setParameter("auto_agent_operation_status","Available")
                .setParameter("auto_agent_role_id",auto_agent_role_id);
        return query.getResultList();
    }


    /**
     * List all auto agents of a given team
     *
     * @param auto_agent_team_id
     * @return list of auto agent entities of a given team
     */
    public List<AutoAgent> list(Long auto_agent_team_id) {
        Query query = entityManager.createQuery("SELECT a FROM AutoAgent a WHERE a.auto_agent_team_id= :auto_agent_team_id")
                .setParameter("auto_agent_team_id",auto_agent_team_id);
        return query.getResultList();
    }
}
