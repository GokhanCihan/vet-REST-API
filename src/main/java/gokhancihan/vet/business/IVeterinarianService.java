package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.request.VeterinarianRequest;
import gokhancihan.vet.dto.response.VeterinarianResponse;
import gokhancihan.vet.entity.Veterinarian;

import java.time.LocalDate;
import java.util.List;

public interface IVeterinarianService {

    VeterinarianResponse getById(Long id);

    List<VeterinarianResponse> getByAvailableDate(LocalDate date);

    VeterinarianResponse create(VeterinarianRequest veterinarianRequest);

    VeterinarianResponse createFor(Long veterinarianId, AvailableDateRequest availableDateRequest);

    VeterinarianResponse deleteFrom(Long veterinarianId, AvailableDateRequest availableDateRequest);

    VeterinarianResponse update(Long id, VeterinarianRequest veterinarianRequest);

    void delete(Long id);

}
