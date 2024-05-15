package gokhancihan.utility.mapper;

import gokhancihan.dto.request.CustomerRequest;
import gokhancihan.dto.response.CustomerResponse;
import gokhancihan.vet.entity.Customer;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerResponse toResponse(Customer customer);


    Customer fromRequest(CustomerRequest customerRequest);

    List<CustomerResponse> toResponses(List<Customer> customers);

    void update(@MappingTarget Customer customer, CustomerRequest customerRequest);
}
