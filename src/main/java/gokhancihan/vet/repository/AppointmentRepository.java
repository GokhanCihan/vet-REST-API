package gokhancihan.vet.repository;

import gokhancihan.vet.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByAppointmentDateAndAnimalId(LocalDateTime appointmentDate, Long animalId);
    Optional<Appointment> findByAppointmentDateAndVeterinarianId(LocalDateTime appointmentDate, Long veterinarianId);
    Optional<Appointment> findByAppointmentDateAndAnimalIdAndVeterinarianId(LocalDateTime appointmentDate,
                                                                            Long animalId, Long veterinarianId);
}
