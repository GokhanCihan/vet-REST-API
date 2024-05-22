package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.VeterinarianAvailableDateRequest;
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

    // Add existing available date to existing veterinarian
    VeterinarianResponse addAvailableDate(VeterinarianAvailableDateRequest veterinarianAvailableDateRequest);

    // Remove an available date from veterinarian
    VeterinarianResponse removeAvailableDate(VeterinarianAvailableDateRequest veterinarianAvailableDateRequest);

    void delete(Long id);

}
