package de.htwsaar.prog3.carrental.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base model containing the primary key.
 *
 * @author Arthur Kelsch
 */
@MappedSuperclass
@Getter
@ToString
@EqualsAndHashCode
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
