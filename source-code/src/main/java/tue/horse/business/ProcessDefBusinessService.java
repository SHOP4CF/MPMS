package tue.horse.business;

import tue.horse.entity.ProcessDef;
import tue.horse.integration.database.service.ProcessDefIntegrationService;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class ProcessDefBusinessService {

    @Inject
    private ProcessDefIntegrationService ProcessDefIntegrationService;


    /**
     * Save the given process entity.
     *
     * @param processEntity new process entity
     * @return persisted work order id
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Long save(ProcessDef processEntity) {
        return ProcessDefIntegrationService.persist(processEntity);
    }



    /**
     * Retrieve a process entity with the given id.
     *
     * @param process_id work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ProcessDef retrieve(Long process_id) {
        return ProcessDefIntegrationService.find(process_id);
    }



    /**
     * Retrieve a process entity with the given name.
     *
     * @param process_name work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ProcessDef retrieveProcessByName(String process_name) {
        return ProcessDefIntegrationService.findProcess(process_name);
    }



    /**
     * Update a process entity.
     *
     * @param processEntity process entity
     * @return refreshed work order
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ProcessDef update(ProcessDef processEntity) {
        return ProcessDefIntegrationService.update(processEntity);
    }


}
