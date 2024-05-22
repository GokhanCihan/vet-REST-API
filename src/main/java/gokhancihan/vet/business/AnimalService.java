package gokhancihan.vet.business;

import gokhancihan.vet.dto.request.AnimalRequest;
import gokhancihan.vet.dto.response.AnimalResponse;
import gokhancihan.vet.repository.AnimalRepository;
import gokhancihan.vet.repository.CustomerRepository;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.exception.RedundantDataException;
import gokhancihan.vet.utility.mapper.AnimalMapper;
import gokhancihan.vet.entity.Animal;
import gokhancihan.vet.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService implements IAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AnimalMapper animalMapper;

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
            throw new NotFoundException("Saved animal couldn't found or save failed");
        }
        return animalMapper.toResponse(savedAnimalFromDb.get());
    }

    @Override
    public AnimalResponse update(Long id, AnimalRequest animalRequest) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        Optional<Customer> customerFromDb = customerRepository.findById(animalRequest.getCustomerId());
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("No animal with id = " + id + " found!");
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
            throw new NotFoundException("No animal with id = " + id + " found!");
        }
        animalRepository.delete(animalFromDb.get());
    }
}
