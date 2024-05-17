package gokhancihan.vet.repository;

import gokhancihan.vet.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByAnimalId(Long animalId);

    List<Vaccine> findByProtectionEndDateBetween(LocalDate startDate, LocalDate endDate);


    Optional<Vaccine> findByAnimalIdAndNameAndCodeAndProtectionStartDateGreaterThanEqualAndProtectionEndDateGreaterThanEqual(
            Long animalId, String name, String code, LocalDate protectionStartDate, LocalDate protectionStartDate1);

    Optional<Vaccine> findByAnimalIdAndNameAndCodeAndProtectionStartDateAndProtectionEndDate(
            Long animalId, String name, String code, LocalDate protectionStartDate, LocalDate protectionEndDate);
}
