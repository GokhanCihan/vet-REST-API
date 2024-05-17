package gokhancihan.vet.utility.mapper;

import gokhancihan.vet.dto.request.VaccineRequest;
import gokhancihan.vet.dto.response.VaccineResponse;
import gokhancihan.vet.entity.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VaccineMapper {

    @Mapping(target = "animal", source = "animal")
    @Mapping(target = "animal.customer", ignore = true)
    VaccineResponse toResponse(Vaccine vaccine);

    @Mapping(target = "animal.id", source = "animalId")
    Vaccine fromRequest(VaccineRequest vaccineRequest);

    List<VaccineResponse> toResponses(List<Vaccine> vaccines);

    void update(@MappingTarget Vaccine vaccine, VaccineRequest vaccineRequest);

}
