package gokhancihan.vet.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gokhancihan.vet.entity.Animal;
import gokhancihan.vet.entity.Customer;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CustomerResponse {

    private Long id;

    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;

}
