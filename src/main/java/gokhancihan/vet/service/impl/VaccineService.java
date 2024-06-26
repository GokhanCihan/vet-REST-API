package gokhancihan.vet.service.impl;

import gokhancihan.vet.service.IVaccineService;
import gokhancihan.vet.dto.request.VaccineRequest;
import gokhancihan.vet.dto.response.VaccineResponse;
import gokhancihan.vet.entity.Animal;
import gokhancihan.vet.entity.Vaccine;
import gokhancihan.vet.repository.AnimalRepository;
import gokhancihan.vet.repository.VaccineRepository;
import gokhancihan.vet.utility.exception.BadRequestException;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.mapper.VaccineMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineService implements IVaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;
    private final VaccineMapper vaccineMapper;

    public VaccineService(VaccineRepository vaccineRepository, AnimalRepository animalRepository, VaccineMapper vaccineMapper) {
        this.vaccineRepository = vaccineRepository;
        this.animalRepository = animalRepository;
        this.vaccineMapper = vaccineMapper;
    }

    @Override
    public VaccineResponse getById(Long id) {
        return vaccineMapper.toResponse(vaccineRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vaccine data with id = " + id + " not found!")));
    }

    @Override
    public List<VaccineResponse> getAll() {
        return vaccineMapper.toResponses(vaccineRepository.findAll());
    }

    @Override
    public List<VaccineResponse> getAllByAnimalId(Long animalId) {
        return vaccineMapper.toResponses(vaccineRepository.findByAnimalId(animalId));
    }

    @Override
    public List<VaccineResponse> getAllByProtectionDate(LocalDate startDate, LocalDate endDate) {
        return vaccineMapper.toResponses(vaccineRepository.findByProtectionEndDateBetween(startDate, endDate));
    }

    @Override
    public VaccineResponse create(VaccineRequest vaccineRequest) {
        Optional<Animal> animalFromDb = animalRepository.findById(vaccineRequest.getAnimalId());
        Optional<Vaccine> vaccineFromDb = vaccineRepository
                .findByAnimalIdAndNameAndCodeAndProtectionStartDateGreaterThanEqualAndProtectionEndDateGreaterThanEqual(
                        vaccineRequest.getAnimalId(), vaccineRequest.getName(), vaccineRequest.getCode(),
                        vaccineRequest.getProtectionStartDate(), vaccineRequest.getProtectionStartDate()
                );
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("Animal data with id = " + vaccineRequest.getAnimalId() + " not found");
        }
        if (vaccineFromDb.isPresent()) {
            throw new BadRequestException(
                    "Animal is already protected by the " + vaccineRequest.getName() + "-" + vaccineRequest.getCode() +
                            " until " + vaccineRequest.getProtectionEndDate());
        }
        Vaccine vaccineToSave = vaccineMapper.fromRequest(vaccineRequest);
        vaccineToSave.setAnimal(animalFromDb.get());
        vaccineRepository.save(vaccineToSave);
        Optional<Vaccine> savedVaccineFromDb = vaccineRepository
                .findByAnimalIdAndNameAndCodeAndProtectionStartDateAndProtectionEndDate(
                        vaccineRequest.getAnimalId(), vaccineRequest.getName(), vaccineRequest.getCode(),
                        vaccineRequest.getProtectionStartDate(), vaccineRequest.getProtectionEndDate()
                );
        if (savedVaccineFromDb.isEmpty()) {
            throw new NotFoundException("Saved vaccine couldn't found or save was unsuccessful");
        }
        return vaccineMapper.toResponse(savedVaccineFromDb.get());
    }

    @Override
    public VaccineResponse update(Long id, VaccineRequest vaccineRequest) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        Optional<Animal> animalFromDb = animalRepository.findById(vaccineRequest.getAnimalId());
        Optional<Vaccine> filteredVaccineFromDb = vaccineRepository
                .findByAnimalIdAndNameAndCodeAndProtectionStartDateGreaterThanEqualAndProtectionEndDateGreaterThanEqual(
                        vaccineRequest.getAnimalId(), vaccineRequest.getName(), vaccineRequest.getCode(),
                        vaccineRequest.getProtectionStartDate(), vaccineRequest.getProtectionStartDate()
                );
        if (vaccineFromDb.isEmpty()) {
            throw new NotFoundException("Vaccine with id = " + id + "not found!");
        }
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("Animal with id = " + vaccineRequest.getAnimalId() + " not found!");
        }
        if (filteredVaccineFromDb.isPresent()) {
            throw new BadRequestException(
                    "Animal is already protected by the " + vaccineRequest.getName() + "-" + vaccineRequest.getCode() +
                            " until " + vaccineRequest.getProtectionEndDate());
        }
        Vaccine vaccineToUpdate = vaccineFromDb.get();
        vaccineMapper.update(vaccineToUpdate, vaccineRequest);
        vaccineRepository.save(vaccineToUpdate);
        Optional<Vaccine> updatedVaccineFromDb = vaccineRepository.findById(id);
        return vaccineMapper.toResponse(updatedVaccineFromDb.get());
    }

    @Override
    public void delete(Long id) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        if (vaccineFromDb.isEmpty()) {
            throw new NotFoundException("Vaccine with id = " + id + " not found!");
        }
        vaccineRepository.delete(vaccineFromDb.get());
    }
}
