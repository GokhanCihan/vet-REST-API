package gokhancihan.vet.repository;

import gokhancihan.vet.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByName(String name);

    List<Animal> findByCustomerId(Long id);

    Optional<Animal> findByNameAndSpeciesAndDateOfBirth(String name, String Species, LocalDate dateOfBirth);
}
