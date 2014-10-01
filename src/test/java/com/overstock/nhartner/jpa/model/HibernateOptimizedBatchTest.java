package com.overstock.nhartner.jpa.model;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.overstock.nhartner.jpa.model.Person;

public class HibernateOptimizedBatchTest extends AbstractBatchTest {

@Override
protected void createInBatches(int batchSize, long records) {
  SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
  StatelessSession session = sessionFactory.openStatelessSession();
  Transaction tx = session.beginTransaction();
  for (long i = 1; i <= records; i++) {
    session.insert(new Person("person" + i, "Name"));
  }
  tx.commit();
  session.close();
}

}
