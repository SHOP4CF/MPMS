package tue.horse.integration.database;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Provides Entity Manager via @Inject annotation.
 */
public class EntityManagerProducer {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * return entity manager for default context.
     *
     * @return default entity manager
     */
    @Produces
    public EntityManager createEntityManger() {
        return this.entityManager;
    }
}
