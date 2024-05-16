package gokhancihan.vet.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class AnimalRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String species;

    @NotNull
    @NotEmpty
    private String breed;

    @NotNull
    @NotEmpty
    private String gender;

    @NotNull
    @NotEmpty
    private String colour;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull(message = "customerId field required!")
    private Long customerId;
}
