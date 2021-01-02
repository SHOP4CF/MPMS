package tue.horse.integration.database.service;

import tue.horse.entity.Agent;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class AgentIntegrationService {

    @Inject
    private EntityManager entityManager;

    /**
     * Persist the given agent entity.
     *
     * @param agentEntity new agent entity
     * @return persisted work order id
     */
    public Long persist(Agent agentEntity) {
        if (agentEntity == null) {
            throw new IllegalArgumentException("agentEntity must not be null");
        }
        entityManager.persist(agentEntity);
        return agentEntity.getAgentId();
    }



    /**
     * Search for an agent entity with the given id.
     *
     * @param agentId work order id
     * @return found work order or null if no one exist
     */
    public Agent find(Long agentId) {
        return entityManager.find(Agent.class, agentId);
    }





    /**
     * Update an agent entity.
     *
     * @param agentEntity entity
     * @return refreshed work order
     */
    public Agent update(Agent agentEntity) {
        if (agentEntity == null) {
            throw new IllegalArgumentException("agentEntity must not be null");
        }
        return entityManager.merge(agentEntity);
    }



    /**
     * List all online agents of a given role
     *
     * @param agent_role_id
     * @return list of all available agents of a given role_id
     */
    public List<Agent> findOnlineAgentByRole(Long agent_role_id) {
        Query query = entityManager.createQuery("SELECT a FROM Agent a WHERE a.agent_online = :agent_online AND a.agent_role_id = :agent_role_id")
                .setParameter("agent_online",true)
                .setParameter("agent_role_id",agent_role_id);
        return query.getResultList();
    }


    /**
     * List all online agents of a given name
     *
     * @param agent_name
     * @return list of all available agents of a given name
     */
    public List<Agent> findOnlineAgentByName(String agent_name) {
        Query query = entityManager.createQuery("SELECT a FROM Agent a WHERE a.agent_online = :agent_online AND a.agent_name = :agent_name")
                .setParameter("agent_online",true)
                .setParameter("agent_name",agent_name);
        return query.getResultList();
    }


}
