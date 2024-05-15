package gokhancihan.vet.dto.response;

import gokhancihan.vet.entity.Animal;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CustomerResponse {

    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;

}
