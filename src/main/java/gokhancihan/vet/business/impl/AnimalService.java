package gokhancihan.vet.business.impl;

import gokhancihan.vet.business.IAnimalService;
import gokhancihan.vet.dto.request.AnimalRequest;
import gokhancihan.vet.dto.response.AnimalResponse;
import gokhancihan.vet.repository.AnimalRepository;
import gokhancihan.vet.repository.CustomerRepository;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.exception.RedundantDataException;
import gokhancihan.vet.utility.mapper.AnimalMapper;
import gokhancihan.vet.entity.Animal;
import gokhancihan.vet.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService implements IAnimalService {

    private final AnimalRepository animalRepository;
    private final CustomerRepository customerRepository;
    private final AnimalMapper animalMapper;

    public AnimalService(AnimalRepository animalRepository, CustomerRepository customerRepository,
                         AnimalMapper animalMapper) {
        this.animalRepository = animalRepository;
        this.customerRepository = customerRepository;
        this.animalMapper = animalMapper;
    }

    @Override
    public AnimalResponse getById(Long id) {
        return animalMapper.toResponse(animalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No animal with id = " + id + " found!")));
    }

    @Override
    public AnimalResponse getByName(String name) {
        return animalMapper.toResponse(animalRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("No animal with name = " + name + " found!")));
    }

    @Override
    public List<AnimalResponse> getAllByCustomerId(Long customerId) {
        Optional<Customer> customerFromDb = customerRepository.findById(customerId);
        if (customerFromDb.isEmpty()) {
            throw new NotFoundException("No customer with id = " + customerId + " found!");
        }
        return animalMapper.toResponses(animalRepository.findByCustomerId(customerFromDb.get().getId()));
    }

    @Override
    public List<AnimalResponse> getAll() {
        return animalMapper.toResponses(animalRepository.findAll());
    }

    @Override
    public AnimalResponse create(AnimalRequest animalRequest) {
        Optional<Animal> animalFromDb = animalRepository.findByNameAndSpeciesAndDateOfBirth(animalRequest.getName(), animalRequest.getSpecies(), animalRequest.getDateOfBirth());
        Optional<Customer> customerFromDb = customerRepository.findById(animalRequest.getCustomerId());
        if (animalFromDb.isPresent()) {
            throw new RedundantDataException("Animal data redundant!");
        }
        if (customerFromDb.isEmpty()) {
            throw new NotFoundException("Customer with id = " + animalRequest.getCustomerId() + "not found!");
        }
        Animal animalToSave = animalMapper.fromRequest(animalRequest);
        animalToSave.setCustomer(customerFromDb.get());
        animalRepository.save(animalToSave);
        Optional<Animal> savedAnimalFromDb = animalRepository.findByNameAndSpeciesAndDateOfBirth(
                animalRequest.getName(), animalRequest.getSpecies(), animalRequest.getDateOfBirth());
        if (savedAnimalFromDb.isEmpty()) {
            throw new RuntimeException("Saved animal couldn't found");
        }
        return animalMapper.toResponse(savedAnimalFromDb.get());
    }

    @Override
    public AnimalResponse update(Long id, AnimalRequest animalRequest) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        Optional<Customer> customerFromDb = customerRepository.findById(animalRequest.getCustomerId());
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("Animal with id = " + id + " not found!");
        }
        if (customerFromDb.isEmpty()) {
            throw new NotFoundException("Customer with id = " + animalRequest.getCustomerId() + "not found!");
        }
        Animal animalToUpdate = animalFromDb.get();
        animalMapper.update(animalToUpdate, animalRequest);
        animalRepository.save(animalToUpdate);
        Optional<Animal> updatedAnimalFromDb = animalRepository.findById(id);
        return animalMapper.toResponse(updatedAnimalFromDb.get());
    }

    @Override
    public void delete(Long id) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("Animal with id = " + id + " not found!");
        }
        animalRepository.delete(animalFromDb.get());
    }
}
