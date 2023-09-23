package com.capstone.progettofinale.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.progettofinale.model.Tratta;
import com.capstone.progettofinale.model.Volo;
import com.capstone.progettofinale.payload.TrattaPayload;
import com.capstone.progettofinale.payload.VoloPayload;
import com.capstone.progettofinale.service.TrasportoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/trasporti")
@Slf4j
public class TrasportiController {

	@Autowired
	private TrasportoService srv;

	@GetMapping("/voli")
	public ResponseEntity<List<VoloPayload>> getAllFligths() {
		List<Volo> lista = srv.findAllVoli();
		return ResponseEntity.ok(lista.stream().map(VoloPayload::new).toList());
	}

	@GetMapping("/voli/{id}")
	public ResponseEntity<VoloPayload> getFlightById(@PathVariable Long id) {
		try {
			Volo a = srv.findVoloById(id);
			return ResponseEntity.ok(new VoloPayload(a));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/voli/cerca")
	public ResponseEntity<List<VoloPayload>> getAllFligths(@RequestParam(required = true) String partenza,
			@RequestParam(required = true) String arrivo,
			@RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-M-dd") LocalDate data) {
		log.info(data.toString());
		List<VoloPayload> lista = srv.findVoliByArrivoEPartenza(partenza, arrivo, data);
		return ResponseEntity.ofNullable(lista);
	}

	@GetMapping("/tratte")
	public ResponseEntity<List<TrattaPayload>> getAllTratte() {
		List<Tratta> lista = srv.findAllTratte();
		return ResponseEntity.ok(lista.stream().map(TrattaPayload::new).toList());
	}

	@GetMapping("/tratte/{id}")
	public ResponseEntity<TrattaPayload> getTrattaById(@PathVariable Long id) {
		try {
			Tratta c = srv.findTrattaById(id);
			return ResponseEntity.ok(new TrattaPayload(c));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/tratte/cerca")
	public ResponseEntity<List<TrattaPayload>> getAllTratte(@RequestParam(required = true) String partenza,
			@RequestParam(required = true) String arrivo,
			@RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-M-dd") LocalDate data) {
		log.info(data.toString());
		List<Tratta> lista = srv.findTratteByArrivoEPartenza(partenza, arrivo, data);
		return ResponseEntity.ofNullable(lista.stream().map(TrattaPayload::new).toList());
	}

	@PostMapping(value = "/voli", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VoloPayload> createFlight(@Valid @RequestBody VoloPayload cp) {

		Volo c = srv.saveVolo(cp);
		return new ResponseEntity<VoloPayload>(new VoloPayload(c), HttpStatus.CREATED);

	}

	@PostMapping("/pulman")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TrattaPayload> creteTratta(@Valid @RequestBody TrattaPayload dp) {
		Tratta d = srv.saveTratta(dp);
		return ResponseEntity.ofNullable(new TrattaPayload(d));
	}

	@PutMapping("/voli/{id}")
	public ResponseEntity<VoloPayload> updateFlight(@PathVariable Long id, @RequestBody VoloPayload cp) {
		cp.setId(id);
		Volo c = this.srv.updateVolo(cp);
		return new ResponseEntity<VoloPayload>(new VoloPayload(c), HttpStatus.OK);
	}

	@PutMapping("/tratte/{id}")
	public ResponseEntity<TrattaPayload> updateTratta(@PathVariable Long id, @RequestBody TrattaPayload dp) {
		dp.setId(id);
		Tratta d = this.srv.updateTratta(dp);
		return new ResponseEntity<TrattaPayload>(new TrattaPayload(d), HttpStatus.OK);
	}

	@DeleteMapping("voli/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteFlight(@PathVariable Long id) {
		this.srv.deleteVolo(id);
		return new ResponseEntity<String>("Rimossa volo cond id: " + id, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("tratte/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteTratta(@PathVariable Long id) {
		this.srv.deleteTratta(id);
		return new ResponseEntity<String>("Rimossa tratta cond id: " + id, HttpStatus.NO_CONTENT);
	}

}
