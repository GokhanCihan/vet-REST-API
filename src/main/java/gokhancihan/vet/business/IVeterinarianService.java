package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.request.VeterinarianRequest;
import gokhancihan.vet.dto.response.VeterinarianResponse;

import java.time.LocalDate;
import java.util.List;

public interface IVeterinarianService {

    VeterinarianResponse getById(Long id);

    List<VeterinarianResponse> getAll();

    List<VeterinarianResponse> getAllByAvailableDate(LocalDate date);

    VeterinarianResponse create(VeterinarianRequest veterinarianRequest);

    VeterinarianResponse update(Long id, VeterinarianRequest veterinarianRequest);

    // Add existing date to existing veterinarian
    VeterinarianResponse addAvailableDate(Long veterinarianId, Long availableDateId);

    // Remove an available date from veterinarian
    VeterinarianResponse deleteFrom(Long veterinarianId, AvailableDateRequest availableDateRequest);

    void delete(Long id);

}
