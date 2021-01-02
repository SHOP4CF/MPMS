package tue.horse.business;

import tue.horse.entity.Role;
import tue.horse.integration.database.service.RoleIntegrationService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class RoleBusinessService {

    @Inject
    private RoleIntegrationService RoleIntegrationService;


    /**
     * Save the given role entity.
     *
     * @param roleEntity new role entity
     * @return persisted work order id
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Long save(Role roleEntity) {
        return RoleIntegrationService.persist(roleEntity);
    }



    /**
     * Retrieve a role entity with the given id.
     *
     * @param role_id work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Role retrieve(Long role_id) {
        return RoleIntegrationService.find(role_id);
    }


    /**
     * Retrieve a role entity with the given name.
     *
     * @param role_name work order id
     * @return found work order or null if no one exist
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Role retrieveRoleByName(String role_name) {
        return RoleIntegrationService.findRole(role_name);
    }


    /**
     * Update a role entity.
     *
     * @param roleEntity role entity
     * @return refreshed work order
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Role update(Role roleEntity ) {
        return RoleIntegrationService.update(roleEntity);
    }



    /**
     * Retrieve all role entities.
     *
     * @return list of role entities
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<Role> retrieveAllRoles() {
        return RoleIntegrationService.list();
    }

}
