package gokhancihan.vet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "veterinarians")
@RequiredArgsConstructor
public class Veterinarian {

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

    @ManyToMany
    @JoinTable(name = "veterinarians_available_dates",
            joinColumns =
                    {@JoinColumn(name = "veterinarian_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "available_date_id")}
    )
    private List<AvailableDate> availableDates;

}
