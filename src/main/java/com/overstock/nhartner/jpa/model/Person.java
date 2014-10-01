package com.overstock.nhartner.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

//import org.hibernate.annotations.Immutable;
//import org.hibernate.annotations.Parameter;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@Entity
@AutoProperty
public class Person {
  @Id
  // sequence-identity causes hibernate to use seq.nextval within the insert statement
  // but disables batch inserts
//  @org.hibernate.annotations.GenericGenerator(
//    name       = "person_id_seq",
//    strategy   = "sequence-identity",
//    parameters = @org.hibernate.annotations.Parameter(name  = "sequence", value = "person_id_seq"))
  // @SequenceGenerator Causes hibernate to select seq.nextval in a separate statement
  // but enables batch inserts
  @SequenceGenerator(
    sequenceName="person_id_seq", name="person_id_seq")
  @GeneratedValue(generator="person_id_seq")
  private Long id;

  @Column private String firstName;

  @Column private String lastName;

  public Person() {
  }

  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() { return id; }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstName() { return firstName; }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public boolean equals(Object other) {
    return Pojomatic.equals(this, other);
  }

  @Override
  public String toString() {
    return Pojomatic.toString(this);
  }

  @Override
  public int hashCode() {
    return Pojomatic.hashCode(this);
  }

}
