package gokhancihan.vet.business.impl;

import gokhancihan.vet.business.IVeterinarianService;
import gokhancihan.vet.dto.request.VeterinarianAvailableDateRequest;
import gokhancihan.vet.dto.request.VeterinarianRequest;
import gokhancihan.vet.dto.response.VeterinarianResponse;
import gokhancihan.vet.entity.AvailableDate;
import gokhancihan.vet.entity.Veterinarian;
import gokhancihan.vet.repository.AvailableDateRepository;
import gokhancihan.vet.repository.VeterinarianRepository;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.exception.RedundantDataException;
import gokhancihan.vet.utility.mapper.VeterinarianMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeterinarianService implements IVeterinarianService {

    private final VeterinarianRepository vetRepository;
    private final VeterinarianMapper vetMapper;
    private final AvailableDateRepository availableDateRepository;

    public VeterinarianService(VeterinarianRepository vetRepository, VeterinarianMapper vetMapper,
                               AvailableDateRepository availableDateRepository) {
        this.vetRepository = vetRepository;
        this.vetMapper = vetMapper;
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public VeterinarianResponse getById(Long id) {
        return vetMapper.toResponse(vetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veterinarian with id = " + id + " not found!")));
    }

    @Override
    public List<VeterinarianResponse> getAll() {
        return vetMapper.toResponses(vetRepository.findAll());
    }

    @Override
    public List<VeterinarianResponse> getAllByAvailableDate(LocalDate date) {
        AvailableDate availableDate = availableDateRepository.findByAvailableDate(date)
                .orElseThrow(() -> new NotFoundException("Available date not created. Create an available date first!"));
        List<Veterinarian> veterinarians = new ArrayList<>();
        veterinarians.addAll(availableDate.getVeterinarians());
        return vetMapper.toResponses(veterinarians);
    }

    @Override
    public VeterinarianResponse create(VeterinarianRequest vetRequest) {
        Optional<Veterinarian> vetFromDb = vetRepository.findByPhoneAndMail(vetRequest.getPhone(), vetRequest.getMail());
        if (vetFromDb.isPresent()) {
            throw new RedundantDataException("Veterinarian data exists!");
        }
        Veterinarian vetToSave = vetMapper.fromRequest(vetRequest);
        vetRepository.save(vetToSave);
        Veterinarian savedVetFromDb = vetRepository.findByPhoneAndMail(vetRequest.getPhone(), vetRequest.getMail())
                .orElseThrow(() -> new NotFoundException("Couldn't fetch saved data or save was unsuccessful!"));
        return vetMapper.toResponse(savedVetFromDb);
    }

    @Override
    public VeterinarianResponse update(Long id, VeterinarianRequest veterinarianRequest) {
        Veterinarian vetFromDb = vetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No veterinarian with id = " + id + " found!"));
        Veterinarian vet = vetFromDb;
        vetMapper.update(vet, veterinarianRequest);
        vetRepository.save(vet);
        vet.setId(vetFromDb.getId());
        return vetMapper.toResponse(vet);
    }

    @Override
    public VeterinarianResponse addAvailableDate(VeterinarianAvailableDateRequest request) {
        AvailableDate dateFromDb = availableDateRepository.findById(request.getAvailableDateId())
                .orElseThrow(() -> new NotFoundException("Available date data with id = " + request.getAvailableDateId() + " not found!"));
        Veterinarian veterinarian = vetRepository.findById(request.getVeterinarianId())
                .orElseThrow(() -> new NotFoundException("Veterinarian data with id =" + request.getVeterinarianId() + " not found!"));
        veterinarian.addAvailableDate(dateFromDb);
        vetRepository.save(veterinarian);
        return vetMapper.toResponse(veterinarian);
    }

    @Override
    public VeterinarianResponse removeAvailableDate(VeterinarianAvailableDateRequest request) {
        AvailableDate dateFromDb = availableDateRepository.findById(request.getAvailableDateId())
                .orElseThrow(() -> new NotFoundException("Available date not found!"));
        Veterinarian veterinarian = vetRepository.findById(request.getVeterinarianId())
                .orElseThrow(() -> new NotFoundException("Available date not found!"));
        veterinarian.removeAvailableDate(dateFromDb);
        vetRepository.save(veterinarian);
        return vetMapper.toResponse(veterinarian);
    }

    @Override
    public void delete(Long id) {
        Veterinarian vetFromDb = vetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No veterinarian with id = " + id + " found!"));
        vetRepository.delete(vetFromDb);
    }
}
