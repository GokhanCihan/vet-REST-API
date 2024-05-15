package gokhancihan.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CustomerRequest {

    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;

}
