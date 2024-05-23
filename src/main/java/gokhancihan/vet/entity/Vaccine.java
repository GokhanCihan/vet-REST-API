package gokhancihan.vet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

}
