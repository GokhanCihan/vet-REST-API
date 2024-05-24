package gokhancihan.vet.service;

import gokhancihan.vet.dto.request.AnimalRequest;
import gokhancihan.vet.dto.response.AnimalResponse;

import java.util.List;

public interface IAnimalService {

    AnimalResponse getById(Long id);

    AnimalResponse getByName(String name);

    List<AnimalResponse> getAllByCustomerId(Long customerId);

    List<AnimalResponse> getAll();

    AnimalResponse create(AnimalRequest animalRequest);

    AnimalResponse update(Long id, AnimalRequest animalRequest);

    void delete(Long id);

}
