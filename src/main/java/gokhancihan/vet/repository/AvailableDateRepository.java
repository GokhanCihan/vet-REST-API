package gokhancihan.vet.repository;

import gokhancihan.vet.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
    Optional<AvailableDate> findByAvailableDate(LocalDate availableDate);
}
