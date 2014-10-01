package com.overstock.nhartner.jpa.model;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.google.common.base.Stopwatch;

public abstract class AbstractBatchTest extends BaseJpaTest {

@Test
public void simplePersist() {
  final int batchSize = 100; // this should match hibernate.jdbc.batch_size in persistence.xml
  final int batches = 10000;
  final long records = batches * batchSize;

  deleteAllPersons();

  assertPersistedCount(0);
  System.out.printf("Inserting %d records in batches of %d\n", records, batchSize);
  Stopwatch watch = new Stopwatch().start();
  createInBatches(batchSize, records);
  System.out.printf("Insertion completed in: %.2fs\n", watch.stop().elapsed(TimeUnit.MILLISECONDS) / 1000.0);
  assertPersistedCount(records);
  System.out.println("done");
}

protected abstract void createInBatches(final int batchSize, final long records);

private void deleteAllPersons() {
  EntityManager manager = entityManagerFactory.createEntityManager();
  manager.getTransaction().begin();
  manager.createQuery("delete from Person").executeUpdate();
  manager.getTransaction().commit();
  manager.close();
}

private void assertPersistedCount(final long records) {
  EntityManager manager = entityManagerFactory.createEntityManager();
  assertEquals(records,
    manager.createQuery("select count(*) from Person").getSingleResult());
  manager.close();
}

}
