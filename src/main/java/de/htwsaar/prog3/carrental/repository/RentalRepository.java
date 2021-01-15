package de.htwsaar.prog3.carrental.repository;

import de.htwsaar.prog3.carrental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    List<Rental> findAllByCarId(Long carId);

    Rental findFirstByCarIdOrderByStart(Long carId);
}
