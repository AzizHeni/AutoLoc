
package tn.esprit.autoloc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(
            @PathVariable Long id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reservation createReservation(
            @RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable Long id,
            @RequestBody Reservation reservation) {

        return reservationService.getReservationById(id)
                .map(existing -> {
                    existing.setDateDebut(reservation.getDateDebut());
                    existing.setDateFin(reservation.getDateFin());
                    existing.setStatut(reservation.getStatut());
                    existing.setClient(reservation.getClient());
                    existing.setVehicule(reservation.getVehicule());

                    return ResponseEntity.ok(
                            reservationService.saveReservation(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (reservationService.getReservationById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
