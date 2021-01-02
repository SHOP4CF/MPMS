package tue.horse.integration.database.service;

import tue.horse.entity.Role;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by KTRAGANOS on 19-4-2017.
 */
public class RoleIntegrationService {

    @Inject
    private EntityManager entityManager;

    /**
     * Persist the given role entity.
     *
     * @param roleEntity new role entity
     * @return persisted work order id
     */
    public Long persist(Role roleEntity) {
        if (roleEntity == null) {
            throw new IllegalArgumentException("roleEntity must not be null");
        }
        entityManager.persist(roleEntity);
        return roleEntity.getRoleId();
    }



    /**
     * Search for a role entity with the given id.
     *
     * @param roleId work order id
     * @return found work order or null if no one exist
     */
    public Role find(Long roleId) {
        return entityManager.find(Role.class, roleId);
    }



    /**
     * Search for a role entity with the given name.
     *
     * @param roleName work order id
     * @return found work order or null if no one exist
     */
    public Role findRole(String roleName) {
        Query query = entityManager.createQuery("SELECT r FROM Role r WHERE r.role_name = :role_name")
                .setParameter("role_name",roleName);
        return (Role) query.getSingleResult();

    }


    /**
     * Update a role entity.
     *
     * @param roleEntity entity
     * @return refreshed work order
     */
    public Role update(Role roleEntity) {
        if (roleEntity == null) {
            throw new IllegalArgumentException("roleEntity must not be null");
        }
        return entityManager.merge(roleEntity);
    }



    /**
     * List all roles
     *
     * @return list of role entities
     */
    public List<Role> list() {
        Query query = entityManager.createQuery("SELECT r FROM Role r");
        return query.getResultList();
    }
}
