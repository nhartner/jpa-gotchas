package com.overstock.nhartner.jpa.model;

import javax.persistence.EntityManager;

import com.overstock.nhartner.jpa.model.Person;

public class JpaBatchTest extends AbstractBatchTest {

@Override
protected void createInBatches(int batchSize, long records) {
  EntityManager entityManager = entityManagerFactory.createEntityManager();
  entityManager.getTransaction().begin();
  for (long i = 1; i <= records; i++) {
    entityManager.persist(new Person("person" + i, "Name"));
  }
  entityManager.getTransaction().commit();
  entityManager.close();
}

}
