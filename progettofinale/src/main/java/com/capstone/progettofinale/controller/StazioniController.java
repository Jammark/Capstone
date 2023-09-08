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

import com.capstone.progettofinale.model.Aereoporto;
import com.capstone.progettofinale.model.StazionePulman;
import com.capstone.progettofinale.payload.AereoportoPayload;
import com.capstone.progettofinale.payload.StazionePulmanPayload;
import com.capstone.progettofinale.service.StazioneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stazioni")
public class StazioniController {

	@Autowired
	private StazioneService srv;

	@GetMapping("/aereoporti")
	public ResponseEntity<List<AereoportoPayload>> getAllAirports() {
		List<Aereoporto> lista = srv.findAllAereoporti();
		return ResponseEntity.ok(lista.stream().map(AereoportoPayload::new).toList());
	}

	@GetMapping("/aereoporti/{id}")
	public ResponseEntity<AereoportoPayload> getCittàById(@PathVariable Long id) {
		try {
			Aereoporto a = srv.findAereoportoById(id);
			return ResponseEntity.ok(new AereoportoPayload(a));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/pulman")
	public ResponseEntity<List<StazionePulmanPayload>> getAllPulmanStations() {
		List<StazionePulman> lista = srv.findAllStazioniPulman();
		return ResponseEntity.ok(lista.stream().map(StazionePulmanPayload::new).toList());
	}

	@GetMapping("/pulman/{id}")
	public ResponseEntity<StazionePulmanPayload> getDestinazioneById(@PathVariable Long id) {
		try {
			StazionePulman c = srv.findStazionePulmanById(id);
			return ResponseEntity.ok(new StazionePulmanPayload(c));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/aereporti")
	public ResponseEntity<AereoportoPayload> createCittà(@Valid @RequestBody AereoportoPayload cp) {

		Aereoporto c = srv.saveAereoporto(cp);
		return new ResponseEntity<AereoportoPayload>(new AereoportoPayload(c), HttpStatus.CREATED);

	}

	@PostMapping("/pulman")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<StazionePulmanPayload> creteDestination(@Valid @RequestBody StazionePulmanPayload dp) {
		StazionePulman d = srv.saveStazionePulman(dp);
		return ResponseEntity.ofNullable(new StazionePulmanPayload(d));
	}

	@PutMapping("/aereoporti/{id}")
	public ResponseEntity<AereoportoPayload> updateCity(@PathVariable Long id, @RequestBody AereoportoPayload cp) {
		Aereoporto c = this.srv.updateAereoporto(cp);
		return new ResponseEntity<AereoportoPayload>(new AereoportoPayload(c), HttpStatus.OK);
	}

	@PutMapping("/pulman/{id}")
	public ResponseEntity<StazionePulmanPayload> updateDestination(@PathVariable Long id,
			@RequestBody StazionePulmanPayload dp) {
		StazionePulman d = this.srv.updateStazionePulman(dp);
		return new ResponseEntity<StazionePulmanPayload>(new StazionePulmanPayload(d), HttpStatus.OK);
	}

	@DeleteMapping("aereoporti/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteDestination(@PathVariable Long id) {
		this.srv.deleteAereoporto(id);
		return new ResponseEntity<String>("Rimossa destinazione cond id: " + id, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("città/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteCity(@PathVariable Long id) {
		this.srv.deleteStazionePulman(id);
		return new ResponseEntity<String>("Rimossa città cond id: " + id, HttpStatus.NO_CONTENT);
	}
}
