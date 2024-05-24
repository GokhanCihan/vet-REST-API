package gokhancihan.vet.service;

import gokhancihan.vet.dto.request.VaccineRequest;
import gokhancihan.vet.dto.response.VaccineResponse;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    VaccineResponse getById(Long id);

    List<VaccineResponse> getAll();

    List<VaccineResponse> getAllByAnimalId(Long animalId);

    List<VaccineResponse> getAllByProtectionDate(LocalDate startDate, LocalDate endDate);

    VaccineResponse create(VaccineRequest vaccineRequest);

    VaccineResponse update(Long id, VaccineRequest vaccineRequest);

    void delete(Long id);
}
