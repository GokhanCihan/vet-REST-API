package gokhancihan.vet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "veterinarians")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "veterinarians_available_dates",
            joinColumns = {@JoinColumn(name = "veterinarian_id")},
            inverseJoinColumns = {@JoinColumn(name = "available_date_id")}
    )
    private Set<AvailableDate> availableDates;

    @OneToMany(mappedBy = "veterinarian", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Appointment> appointments;

    public void addAvailableDate(AvailableDate availableDate) {
        this.availableDates.add(availableDate);
    }

    public void removeAvailableDate(AvailableDate availableDate) {
        this.availableDates.remove(availableDate);
    }
}
