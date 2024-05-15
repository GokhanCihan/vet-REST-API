package gokhancihan.repository;

import gokhancihan.vet.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByName(String name);

    List<Animal> findByCustomerId(Long id);

    Optional<Animal> findByNameAndCustomerId(String name, Long customerId);
}
