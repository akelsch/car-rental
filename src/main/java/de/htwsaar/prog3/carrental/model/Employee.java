package de.htwsaar.prog3.carrental.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Employee model for Hibernate.
 *
 * @author Julian Quint
 */
@Entity
@Table
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String surname;
}
