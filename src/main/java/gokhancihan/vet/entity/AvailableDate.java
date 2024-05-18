package gokhancihan.vet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "available_dates")
@RequiredArgsConstructor
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "available_date", nullable = false, unique = true)
    private LocalDate availableDate;

    @ManyToMany(mappedBy = "availableDates")
    @JsonIgnore
    private List<Veterinarian> veterinarians;
}