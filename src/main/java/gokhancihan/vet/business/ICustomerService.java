package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.CustomerRequest;
import gokhancihan.vet.dto.response.CustomerResponse;

import java.util.List;

public interface ICustomerService {

    CustomerResponse getById(Long id);

    CustomerResponse getByName(String name);

    List<CustomerResponse> getAll();

    CustomerResponse create(CustomerRequest customerRequest);

    CustomerResponse update(Long id, CustomerRequest customerRequest);

    void delete(Long id);

}
