package gokhancihan.vet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "animals")
@RequiredArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    @Column(nullable = false)
    private String breed;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String colour;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
}
