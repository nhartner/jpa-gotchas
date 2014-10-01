package com.overstock.nhartner.jpa.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;


public abstract class BaseJpaTest {

  protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");

  static {
    Logger.getRootLogger().setLevel(Level.WARN);
    Logger.getRootLogger().addAppender(new ConsoleAppender());
  }

  @Before public void delete() {
    EntityManager manager = entityManagerFactory.createEntityManager();
    manager.getTransaction().begin();
    manager.createQuery("delete from Person");
    manager.getTransaction().commit();
    manager.close();
  }

}
