package gokhancihan.vet.utility.mapper;

import gokhancihan.vet.dto.request.CustomerRequest;
import gokhancihan.vet.dto.response.CustomerResponse;
import gokhancihan.vet.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "id", source = "id")
    CustomerResponse toResponse(Customer customer);

    Customer fromRequest(CustomerRequest customerRequest);

    List<CustomerResponse> toResponses(List<Customer> customers);

    void update(@MappingTarget Customer customer, CustomerRequest customerRequest);
}
