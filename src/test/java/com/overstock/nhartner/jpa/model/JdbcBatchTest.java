package com.overstock.nhartner.jpa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

public class JdbcBatchTest extends AbstractBatchTest {

protected void createInBatches(final int batchSize, final long records) {
  Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
  Transaction tx = session.beginTransaction();
  session.doWork(new Work() {
    public void execute(Connection connection) throws SQLException {
      try(
        PreparedStatement insertStatement =
         connection.prepareStatement(
          "insert into person (id, firstname, lastname) " +
          "values (person_id_seq.nextval, ?, ?)")) {
        for (int i = 1; i <= records; i++) {
          insertStatement.setString(1, "John" + i);
          insertStatement.setString(2, "Doe");
          insertStatement.addBatch();
          if(i % batchSize == 0) {
            insertStatement.executeBatch();
          }
        }
      }
    }
  });
  tx.commit();
}

}
