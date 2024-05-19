package gokhancihan.vet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
            joinColumns = {@JoinColumn(name = "veterinarian_id")},
            inverseJoinColumns = {@JoinColumn(name = "available_date_id")}
    )
    private Set<AvailableDate> availableDates;

    public void addAvailableDate(AvailableDate availableDate) {
        this.availableDates.add(availableDate);
        availableDate.getVeterinarians().add(this);
    }

    public void removeAvailableDate(Long availableDateId) {
        AvailableDate availableDate = this.availableDates.stream()
                .filter(date -> date.getId() == availableDateId).findFirst()
                .orElse(null);
        if (availableDate != null) {
            this.availableDates.remove(availableDate);
            availableDate.getVeterinarians().remove(this);
        }
    }

    ;
}
