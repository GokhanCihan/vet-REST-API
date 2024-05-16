package gokhancihan.vet.utility.mapper;

import gokhancihan.vet.dto.request.AnimalRequest;
import gokhancihan.vet.dto.response.AnimalResponse;
import gokhancihan.vet.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = CustomerMapper.class)
public interface AnimalMapper {

    @Mapping(target = "customer", source = "customer")
    AnimalResponse toResponse(Animal animal);

    @Mapping(target = "customer.id", source = "customerId")
    Animal fromRequest(AnimalRequest animalRequest);

    List<AnimalResponse> toResponses(List<Animal> animals);

    void update(@MappingTarget Animal animal, AnimalRequest animalRequest);

}
