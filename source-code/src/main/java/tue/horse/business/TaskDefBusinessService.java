package tue.horse.business;

import tue.horse.entity.TaskDef;
import tue.horse.integration.database.service.TaskDefIntegrationService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class TaskDefBusinessService {

    @Inject
    private TaskDefIntegrationService TaskDefIntegrationService;


    /**
     * Save the given task entity.
     *
     * @param taskEntity new task entity
     * @return persisted work order id
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Long save(TaskDef taskEntity) {
        return TaskDefIntegrationService.persist(taskEntity);
    }



    /**
     * Retrieve a task entity with the given id.
     *
     * @param task_id work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public TaskDef retrieve(Long task_id) {
        return TaskDefIntegrationService.find(task_id);
    }



    /**
     * Update a task entity.
     *
     * @param taskEntity task entity
     * @return refreshed work order
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public TaskDef update(TaskDef taskEntity) {
        return TaskDefIntegrationService.update(taskEntity);
    }



    /**
     * Retrieve all tasks of a given process
     *
     * @return list of task entities of a given process
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<TaskDef> retrieveAll(Long task_process_id) {
        return TaskDefIntegrationService.list(task_process_id);
    }

}
