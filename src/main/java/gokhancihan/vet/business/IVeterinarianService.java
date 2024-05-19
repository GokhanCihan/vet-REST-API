package gokhancihan.vet.business;

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

    void delete(Long id);

}
