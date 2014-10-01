package com.overstock.nhartner.jpa.model;

import javax.persistence.EntityManager;

import com.overstock.nhartner.jpa.model.Person;

public class JpaOptimizedBatchTest extends AbstractBatchTest {

@Override
protected void createInBatches(int batchSize, long records) {
  EntityManager entityManager = entityManagerFactory.createEntityManager();
  entityManager.getTransaction().begin();

  for (long i = 1; i <= records; i++) {
    entityManager.persist(new Person("person" + i, "Name"));
    if (i % batchSize == 0) {
      entityManager.flush();
      entityManager.clear();
    }
  }

  entityManager.getTransaction().commit();
  entityManager.close();
}

}
