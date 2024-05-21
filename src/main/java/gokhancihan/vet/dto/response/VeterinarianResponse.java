package gokhancihan.vet.dto.response;

import gokhancihan.vet.entity.AvailableDate;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
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
