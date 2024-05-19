package gokhancihan.vet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "customers")
@RequiredArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @OneToMany(mappedBy = "customer")
    private List<Animal> animals;
}
