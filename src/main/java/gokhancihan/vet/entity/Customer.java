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
    @Column(name = "customer_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String mail;

    private String address;

    private String city;

    @OneToMany(mappedBy = "customer")
    private List<Animal> animals;
}
