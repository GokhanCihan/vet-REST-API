package gokhancihan.vet.utility.mapper;

import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.response.AvailableDateResponse;
import gokhancihan.vet.entity.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = VeterinarianMapper.class)
public interface AvailableDateMapper {

    @Mapping(target = "id", source = "id")
    AvailableDateResponse toResponse(AvailableDate availableDate);

    AvailableDate fromRequest(AvailableDateRequest availableDateRequest);

    List<AvailableDateResponse> toResponses(List<AvailableDate> availableDates);

    void update(@MappingTarget AvailableDate availableDate, AvailableDateRequest availableDateRequest);

}
