package gokhancihan.vet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "vaccines")
@RequiredArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(name = "protection_start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate protectionStartDate;

    @Column(name = "protection_end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate protectionEndDate;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

}
