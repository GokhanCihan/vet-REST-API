package gokhancihan.vet.dto.response;

import gokhancihan.vet.entity.Customer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AnimalResponse {

    private Long id;

    private String name;

    private String species;

    private String breed;

    private String gender;

    private String colour;

    private LocalDate dateOfBirth;

    private CustomerResponse customer;
}
