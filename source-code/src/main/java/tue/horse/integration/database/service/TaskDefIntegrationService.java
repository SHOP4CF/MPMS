package tue.horse.integration.database.service;

import tue.horse.entity.TaskDef;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class TaskDefIntegrationService {

    @Inject
    private EntityManager entityManager;

    /**
     * Persist the given task entity.
     *
     * @param taskEntity new task entity
     * @return persisted work order id
     */
    public Long persist(TaskDef taskEntity) {
        if (taskEntity == null) {
            throw new IllegalArgumentException("taskEntity must not be null");
        }
        entityManager.persist(taskEntity);
        return taskEntity.getTaskId();
    }



    /**
     * Search for a task entity with the given id.
     *
     * @param taskId work order id
     * @return found work order or null if no one exist
     */
    public TaskDef find(Long taskId) {
        return entityManager.find(TaskDef.class, taskId);
    }





    /**
     * Update a task entity.
     *
     * @param taskEntity entity
     * @return refreshed work order
     */
    public TaskDef update(TaskDef taskEntity) {
        if (taskEntity == null) {
            throw new IllegalArgumentException("taskEntity must not be null");
        }
        return entityManager.merge(taskEntity);
    }



    /**
     * List all tasks of a given process
     *
     * @param task_process_id
     * @return list of task entities of a given process
     */
    public List<TaskDef> list(Long task_process_id) {
        Query query = entityManager.createQuery("SELECT t FROM TaskDef t WHERE t.task_process_id = :task_process_id")
                .setParameter("task_process_id",task_process_id);
        return query.getResultList();
    }
}
