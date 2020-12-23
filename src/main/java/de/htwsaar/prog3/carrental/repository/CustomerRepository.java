package de.htwsaar.prog3.carrental.repository;

import de.htwsaar.prog3.carrental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByIdNotAndIdNumber(Long id, String idNumber);

    boolean existsByIdNotAndDriverLicenseNumber(Long id, String driverLicenseNumber);
}
