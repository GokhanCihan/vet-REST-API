package gokhancihan.repository;

import gokhancihan.vet.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String customerName);

    Optional<Customer> findByNameAndMail(String name, String mail);
}