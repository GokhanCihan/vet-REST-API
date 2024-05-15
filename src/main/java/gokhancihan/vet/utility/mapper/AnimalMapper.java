package gokhancihan.vet.utility.mapper;

import gokhancihan.vet.dto.request.AnimalRequest;
import gokhancihan.vet.dto.response.AnimalResponse;
import gokhancihan.vet.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AnimalMapper {

    AnimalMapper MAPPER = Mappers.getMapper(AnimalMapper.class);

    AnimalResponse toResponse(Animal animal);


    Animal fromRequest(AnimalRequest animalRequest);

    List<AnimalResponse> toResponses(List<Animal> animals);

    void update(@MappingTarget Animal animal, AnimalRequest animalRequest);

}
