package gokhancihan.vet.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerResponse {

    private Long id;

    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;

}
