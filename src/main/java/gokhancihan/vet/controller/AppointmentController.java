package gokhancihan.vet.controller;

import gokhancihan.vet.service.IAppointmentService;
import gokhancihan.vet.dto.request.AppointmentRequest;
import gokhancihan.vet.dto.response.AppointmentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/appointment")
@Tag(name = "Appointment Management")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve appointment information")
    public AppointmentResponse getById(@PathVariable("id") Long id) {
        return appointmentService.getById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all appointments")
    public List<AppointmentResponse> getAll() {
        return appointmentService.getAll();
    }

    @GetMapping("/all/veterinarianId={id}&startDate={startDate}&endDate={endDate}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all appointments of a veterinarian in a date range")
    public List<AppointmentResponse> getAllByVeterinarianAndDateRange(
            @PathVariable("id") Long veterinarianId,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {
        return appointmentService.getAllInRangeForVeterinarian(veterinarianId, startDate, endDate);
    }

    @GetMapping("/all/animalId={id}&startDate{startDate}&endDate{endDate}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieve list of all appointments of an animal in a date range")
    public List<AppointmentResponse> getAllByAnimalAndDateRange(
            @PathVariable("id") Long animalId,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {
        return appointmentService.getAllInRangeForAnimal(animalId, startDate, endDate);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create appointment with a veterinarian for an animal")
    public AppointmentResponse create(@RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.create(appointmentRequest);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update appointment information")
    public AppointmentResponse update(@PathVariable("id") Long id, @RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.update(id, appointmentRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete appointment")
    public void delete(@PathVariable("id") Long id) {
        appointmentService.delete(id);
    }
}
