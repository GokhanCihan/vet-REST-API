package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.response.AvailableDateResponse;
import gokhancihan.vet.dto.response.VeterinarianResponse;
import gokhancihan.vet.entity.AvailableDate;

import java.util.List;

public interface IAvailableDateService {

    AvailableDateResponse getById(Long id);

    List<AvailableDateResponse> getAll();

    AvailableDateResponse create(AvailableDateRequest availableDateRequest);

    // Add existing date to existing veterinarian
    // Create new available date if not exists
    AvailableDateResponse createFor(Long veterinarianId, AvailableDateRequest availableDateRequest);

    // Remove an available date from veterinarian
    AvailableDateResponse deleteFrom(Long veterinarianId, AvailableDateRequest availableDateRequest);

    AvailableDateResponse update(Long id, AvailableDateRequest availableDateRequest);

    void delete(Long id);

}
