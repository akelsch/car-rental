package de.htwsaar.prog3.carrental.repository;

import de.htwsaar.prog3.carrental.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByDriverLicenseId(String driverLicenseId);

    boolean existsByIdNotAndIdNumber(Long id, String idNumber);

    boolean existsByIdNotAndDriverLicenseId(Long id, String driverLicenseId);
}
