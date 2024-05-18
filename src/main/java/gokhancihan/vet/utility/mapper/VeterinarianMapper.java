package gokhancihan.vet.utility.mapper;

import gokhancihan.vet.dto.request.VaccineRequest;
import gokhancihan.vet.dto.request.VeterinarianRequest;
import gokhancihan.vet.dto.response.VeterinarianResponse;
import gokhancihan.vet.entity.Veterinarian;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = AvailableDateMapper.class)
public interface VeterinarianMapper {

    @Mapping(target = "availableDates", source = "availableDates")
    VeterinarianResponse toResponse(Veterinarian veterinarian);

    Veterinarian fromRequest(VaccineRequest vaccineRequest);

    List<VeterinarianResponse> toResponses(List<Veterinarian> veterinarians);

    void update(@MappingTarget Veterinarian veterinarian, VeterinarianRequest veterinarianRequest);

}
