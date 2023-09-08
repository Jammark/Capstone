package com.capstone.progettofinale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.progettofinale.model.Tratta;
import com.capstone.progettofinale.model.Volo;
import com.capstone.progettofinale.payload.TrattaPayload;
import com.capstone.progettofinale.payload.VoloPayload;
import com.capstone.progettofinale.service.TrasportoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trasporti")
public class TrasportiController {

	@Autowired
	private TrasportoService srv;

	@GetMapping("/voli")
	public ResponseEntity<List<VoloPayload>> getAllFligths() {
		List<Volo> lista = srv.findAllVoli();
		return ResponseEntity.ok(lista.stream().map(VoloPayload::new).toList());
	}

	@GetMapping("/voli/{id}")
	public ResponseEntity<VoloPayload> getCittàById(@PathVariable Long id) {
		try {
			Volo a = srv.findVoloById(id);
			return ResponseEntity.ok(new VoloPayload(a));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/tratte")
	public ResponseEntity<List<TrattaPayload>> getAllTratte() {
		List<Tratta> lista = srv.findAllTratte();
		return ResponseEntity.ok(lista.stream().map(TrattaPayload::new).toList());
	}

	@GetMapping("/tratte/{id}")
	public ResponseEntity<TrattaPayload> getDestinazioneById(@PathVariable Long id) {
		try {
			Tratta c = srv.findTrattaById(id);
			return ResponseEntity.ok(new TrattaPayload(c));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/voli")
	public ResponseEntity<VoloPayload> createCittà(@Valid @RequestBody VoloPayload cp) {

		Volo c = srv.saveVolo(cp);
		return new ResponseEntity<VoloPayload>(new VoloPayload(c), HttpStatus.CREATED);

	}

	@PostMapping("/pulman")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TrattaPayload> creteDestination(@Valid @RequestBody TrattaPayload dp) {
		Tratta d = srv.saveTratta(dp);
		return ResponseEntity.ofNullable(new TrattaPayload(d));
	}

	@PutMapping("/voli/{id}")
	public ResponseEntity<VoloPayload> updateCity(@PathVariable Long id, @RequestBody VoloPayload cp) {
		cp.setId(id);
		Volo c = this.srv.updateVolo(cp);
		return new ResponseEntity<VoloPayload>(new VoloPayload(c), HttpStatus.OK);
	}

	@PutMapping("/tratte/{id}")
	public ResponseEntity<TrattaPayload> updateDestination(@PathVariable Long id, @RequestBody TrattaPayload dp) {
		dp.setId(id);
		Tratta d = this.srv.updateTratta(dp);
		return new ResponseEntity<TrattaPayload>(new TrattaPayload(d), HttpStatus.OK);
	}

	@DeleteMapping("voli/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteDestination(@PathVariable Long id) {
		this.srv.deleteVolo(id);
		return new ResponseEntity<String>("Rimossa volo cond id: " + id, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("tratte/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteCity(@PathVariable Long id) {
		this.srv.deleteTratta(id);
		return new ResponseEntity<String>("Rimossa tratta cond id: " + id, HttpStatus.NO_CONTENT);
	}

}
