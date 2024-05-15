package gokhancihan.business;

import gokhancihan.dto.request.AnimalRequest;
import gokhancihan.dto.response.AnimalResponse;

import java.util.List;

public interface IAnimalService {

    AnimalResponse getById(Long id);

    AnimalResponse getByName(String name);

    List<AnimalResponse> getAllByCustomerName(String CustomerName);

    List<AnimalResponse> getAll();

    AnimalResponse create(AnimalRequest animalRequest);

    AnimalResponse update(Long id, AnimalRequest animalRequest);

    void delete(Long id);

}
