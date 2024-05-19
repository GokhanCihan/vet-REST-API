package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.AvailableDateRequest;
import gokhancihan.vet.dto.response.AvailableDateResponse;

import java.util.List;

public interface IAvailableDateService {

    AvailableDateResponse getById(Long id);

    List<AvailableDateResponse> getAll();

    AvailableDateResponse create(AvailableDateRequest availableDateRequest);

    AvailableDateResponse update(Long id, AvailableDateRequest availableDateRequest);

    void delete(Long id);

}
