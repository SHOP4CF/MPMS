package tue.horse.integration.database.service;

import tue.horse.entity.ProcessDef;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class ProcessDefIntegrationService {

    @Inject
    private EntityManager entityManager;

    /**
     * Persist the given process entity.
     *
     * @param processEntity new process entity
     * @return persisted work order id
     */
    public Long persist(ProcessDef processEntity) {
        if (processEntity == null) {
            throw new IllegalArgumentException("processEntity must not be null");
        }
        entityManager.persist(processEntity);
        return processEntity.getProcessId();
    }



    /**
     * Search for a process entity with the given id.
     *
     * @param processId work order id
     * @return found work order or null if no one exist
     */
    public ProcessDef find(Long processId) {
        return entityManager.find(ProcessDef.class, processId);
    }


    /**
     * Search for a process entity with the given name.
     *
     * @param processName work order id
     * @return found work order or null if no one exist
     */
    public ProcessDef findProcess(String processName) {
        Query query = entityManager.createQuery("SELECT p FROM ProcessDef p WHERE p.process_name = :process_name")
                .setParameter("process_name",processName);
        return (ProcessDef) query.getSingleResult();

    }


    /**
     * Update a process entity.
     *
     * @param processEntity entity
     * @return refreshed work order
     */
    public ProcessDef update(ProcessDef processEntity) {
        if (processEntity == null) {
            throw new IllegalArgumentException("processEntity must not be null");
        }
        return entityManager.merge(processEntity);
    }


}
