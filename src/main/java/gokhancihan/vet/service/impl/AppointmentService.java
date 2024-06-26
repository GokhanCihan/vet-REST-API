package gokhancihan.vet.service.impl;

import gokhancihan.vet.service.IAppointmentService;
import gokhancihan.vet.dto.request.AppointmentRequest;
import gokhancihan.vet.dto.response.AppointmentResponse;
import gokhancihan.vet.entity.Animal;
import gokhancihan.vet.entity.Appointment;
import gokhancihan.vet.entity.AvailableDate;
import gokhancihan.vet.entity.Veterinarian;
import gokhancihan.vet.repository.AnimalRepository;
import gokhancihan.vet.repository.AppointmentRepository;
import gokhancihan.vet.repository.AvailableDateRepository;
import gokhancihan.vet.repository.VeterinarianRepository;
import gokhancihan.vet.utility.Helper;
import gokhancihan.vet.utility.exception.BadRequestException;
import gokhancihan.vet.utility.exception.InvalidRequestParameterException;
import gokhancihan.vet.utility.exception.NotFoundException;
import gokhancihan.vet.utility.mapper.AppointmentMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final AppointmentMapper appointmentMapper;
    private final AnimalRepository animalRepo;
    private final VeterinarianRepository veterinarianRepo;
    private final AvailableDateRepository availableDateRepository;

    public AppointmentService(AppointmentRepository appointmentRepo, AppointmentMapper appointmentMapper,
                              AnimalRepository animalRepo, VeterinarianRepository veterinarianRepo,
                              AvailableDateRepository availableDateRepository) {
        this.appointmentRepo = appointmentRepo;
        this.appointmentMapper = appointmentMapper;
        this.animalRepo = animalRepo;
        this.veterinarianRepo = veterinarianRepo;
        this.availableDateRepository = availableDateRepository;
    }

    @Override
    public AppointmentResponse getById(Long id) {
        return appointmentMapper.toResponse(appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment data with id = " + id + " not found!")));
    }

    @Override
    public List<AppointmentResponse> getAll() {
        return appointmentMapper.toResponses(appointmentRepo.findAll());
    }

    @Override
    public List<AppointmentResponse> getAllInRangeForVeterinarian(Long veterinarianId, LocalDate startDate, LocalDate endDate) {
        return appointmentMapper.toResponses(appointmentRepo.findAllByVeterinarianIdAndAppointmentDateBetween(
                veterinarianId,
                startDate.atStartOfDay(),
                endDate.atStartOfDay()));
    }

    @Override
    public List<AppointmentResponse> getAllInRangeForAnimal(Long animalId, LocalDate startDate, LocalDate endDate) {
        return appointmentMapper.toResponses(appointmentRepo.findAllByAnimalIdAndAppointmentDateBetween(
                animalId,
                startDate.atStartOfDay(),
                endDate.atStartOfDay()
        ));
    }

    @Override
    public AppointmentResponse create(AppointmentRequest request) {
        Optional<Animal> animalFromDb = animalRepo.findById(request.getAnimalId());
        Optional<Veterinarian> vetFromDb = veterinarianRepo.findById(request.getVeterinarianId());
        Optional<Appointment> appointmentForAnimal = appointmentRepo.findByAppointmentDateAndAnimalId(
                request.getAppointmentDate(), request.getAnimalId());
        Optional<AvailableDate> dateFromDb = availableDateRepository.findByAvailableDate(
                request.getAppointmentDate().toLocalDate());
        Optional<Appointment> appointmentForVet = appointmentRepo.findByAppointmentDateAndVeterinarianId(
                request.getAppointmentDate(), request.getVeterinarianId());
        if (!Helper.isExactHour(request.getAppointmentDate())) {
            throw new InvalidRequestParameterException("Each Appointment starts at the beginning of an hour. " +
                    "Please provide a time accordingly.");
        }
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("Animal with id = " + request.getAnimalId() + " not found!");
        }
        if (vetFromDb.isEmpty()) {
            throw new NotFoundException("Veterinarian with id = " + request.getVeterinarianId() + " not found!");
        }
        if (dateFromDb.isEmpty()) {
            throw new NotFoundException("No available date created for this date!");
        }
        if (!dateFromDb.get().getVeterinarians().contains(vetFromDb.get())) {
            throw new NotFoundException("Veterinarian with id = " + request.getVeterinarianId() +
                    " is not working on this date!");
        }
        if (appointmentForAnimal.isPresent()) {
            throw new BadRequestException("There is another appointment at this time for the animal");
        }
        if (appointmentForVet.isPresent()) {
            throw new BadRequestException("There is another appointment at this time for the veterinarian");
        }
        Appointment appointmentToSave = appointmentMapper.fromRequest(request);
        appointmentRepo.save(appointmentToSave);
        Appointment savedAppointment = appointmentRepo
                .findByAppointmentDateAndAnimalIdAndVeterinarianId(request.getAppointmentDate(), request.getAnimalId(),
                        request.getVeterinarianId())
                .orElseThrow(() -> new RuntimeException("Couldn't fetch saved data!"));
        System.out.println(savedAppointment.getAnimal().getName());
        savedAppointment.setAnimal(animalFromDb.get());
        savedAppointment.setVeterinarian(vetFromDb.get());
        return appointmentMapper.toResponse(savedAppointment);
    }

    @Override
    public AppointmentResponse update(Long id, AppointmentRequest request) {
        // TODO: check for duplicates
        Optional<Animal> animalFromDb = animalRepo.findById(request.getAnimalId());
        Optional<Veterinarian> vetFromDb = veterinarianRepo.findById(request.getVeterinarianId());
        Optional<Appointment> appointmentFromDb = appointmentRepo.findById(id);
        if (!Helper.isExactHour(request.getAppointmentDate())) {
            throw new InvalidRequestParameterException("Each Appointment starts at the beginning of an hour. " +
                    "Please provide a time accordingly.");
        }
        if (appointmentFromDb.isEmpty()) {
            throw new NotFoundException("Appointment with id = " + id + " not found!");
        }
        if (animalFromDb.isEmpty()) {
            throw new NotFoundException("Animal with id = " + request.getAnimalId() + " not found!");
        }
        if (vetFromDb.isEmpty()) {
            throw new NotFoundException("Veterinarian with id = " + request.getVeterinarianId() + " not found!");
        }
        appointmentRepo.findByAppointmentDateAndAnimalId(request.getAppointmentDate(), request.getAnimalId())
                .orElseThrow(() -> new BadRequestException("There is an appointment at this time for the animal"));
        appointmentRepo.findByAppointmentDateAndVeterinarianId(request.getAppointmentDate(), request.getVeterinarianId())
                .orElseThrow(() -> new BadRequestException("There is an appointment at this time for the veterinarian"));
        Appointment appointment = appointmentFromDb.get();
        appointmentMapper.update(appointment, request);
        appointmentRepo.save(appointment);
        appointment.setId(appointmentFromDb.get().getId());
        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public void delete(Long id) {
        Appointment appointmentFromDb = appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment with id = " + id + " not found!"));
        appointmentRepo.delete(appointmentFromDb);
    }
}
