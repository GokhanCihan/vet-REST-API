package gokhancihan.vet.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    private String mail;

    @NotNull
    @NotEmpty
    private String address;

    @NotNull
    @NotEmpty
    private String city;

}
