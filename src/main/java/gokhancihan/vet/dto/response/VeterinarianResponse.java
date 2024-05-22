package gokhancihan.vet.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class VeterinarianResponse {

    private Long id;

    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;

    private List<AvailableDateResponse> availableDates;

}
