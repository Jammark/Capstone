package com.capstone.progettofinale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.progettofinale.model.Città;
import com.capstone.progettofinale.model.Destinazione;
import com.capstone.progettofinale.payload.CittàPayload;
import com.capstone.progettofinale.payload.DestinazionePayload;
import com.capstone.progettofinale.service.MetaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mete")
public class MeteController {

	@Autowired
	private MetaService mSrv;

	@GetMapping("/città")
	public ResponseEntity<List<CittàPayload>> getAllCities() {
		List<Città> lista = mSrv.findAllCittà();
		return ResponseEntity.ok(lista.stream().map(CittàPayload::new).toList());
	}

	@GetMapping("/città/{id}")
	public ResponseEntity<CittàPayload> getCittàById(@PathVariable Long id) {
		try {
			Città c = mSrv.findCittàById(id);
			return ResponseEntity.ok(new CittàPayload(c));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/destinazioni")
	public ResponseEntity<List<DestinazionePayload>> getAllDestinations() {
		List<Destinazione> lista = mSrv.findAllDestinazioni();
		return ResponseEntity.ok(lista.stream().map(DestinazionePayload::new).toList());
	}

	@GetMapping("/destinazioni/{id}")
	public ResponseEntity<DestinazionePayload> getDestinazioneById(@PathVariable Long id) {
		try {
			Destinazione c = mSrv.findDestinazioneById(id);
			return ResponseEntity.ok(new DestinazionePayload(c));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("destinazioni/most_rated")
	public ResponseEntity<List<DestinazionePayload>> getMostRated() {
		List<Destinazione> lista = mSrv.findAllDestinazioni();
		return ResponseEntity.ok(lista.stream().map(DestinazionePayload::new).toList());
	}

	@GetMapping("città/most_rated")
	public ResponseEntity<List<CittàPayload>> getMostRatedCities() {
		List<Città> lista = mSrv.findMostRatedCities();
		return ResponseEntity.ok(lista.stream().map(CittàPayload::new).toList());
	}

	@PostMapping("/città")
	public ResponseEntity<CittàPayload> createCittà(@Valid @RequestBody CittàPayload cp) {
		try {
		Città c = mSrv.saveCittà(cp);
		return new ResponseEntity<CittàPayload>(new CittàPayload(c), HttpStatus.CREATED);
	} catch (java.io.IOException e) {
		e.printStackTrace();
		throw new IllegalStateException("errore salvattagio immagine.");
	}
	}

	@PostMapping("/destinazioni")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<DestinazionePayload> creteDestination(@Valid @RequestBody DestinazionePayload dp) {
		Destinazione d = mSrv.saveDestinazione(dp);
		return ResponseEntity.ofNullable(new DestinazionePayload(d));
	}

	@PatchMapping("/destinazioni/{destId}")
	public ResponseEntity<DestinazionePayload> addCity(@PathVariable Long destId, @RequestBody CittàPayload cp) {

		Destinazione d = this.mSrv.aggiungiCittà(cp, destId);
		return new ResponseEntity<DestinazionePayload>(new DestinazionePayload(d), HttpStatus.OK);
	}

	@PatchMapping("/destinazioni({destId}")
	public ResponseEntity<DestinazionePayload> removeCity(@PathVariable Long destId, @RequestBody CittàPayload cp) {
		Destinazione d = this.mSrv.removeCittà(cp);
		return new ResponseEntity<DestinazionePayload>(new DestinazionePayload(d), HttpStatus.OK);
	}

	@PutMapping("/città/{id}")
	public ResponseEntity<CittàPayload> updateCity(@PathVariable Long id, @RequestBody CittàPayload cp) {
		cp.setId(id);
		Città c = this.mSrv.updateCittà(cp);
		return new ResponseEntity<CittàPayload>(new CittàPayload(c), HttpStatus.OK);
	}

	@PutMapping("/destinazioni/{id}")
	public ResponseEntity<DestinazionePayload> updateDestination(@PathVariable Long id,
			@RequestBody DestinazionePayload dp) {
		dp.setId(id);
		Destinazione d = this.mSrv.updateDestinazione(dp);
		return new ResponseEntity<DestinazionePayload>(new DestinazionePayload(d), HttpStatus.OK);
	}

	@DeleteMapping("destinazioni/{destId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteDestination(@PathVariable Long destId) {
		this.mSrv.deleteDestinazione(destId);
		return new ResponseEntity<String>("Rimossa destinazione cond id: " + destId, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("città/{cityId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<String> deleteCity(@PathVariable Long cityId) {
		this.mSrv.deleteCittà(cityId);
		return ResponseEntity.ofNullable("Rimossa città cond id: " + cityId);
	}

	@GetMapping(value = "/image/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody byte[] getImage(@PathVariable String img) throws java.io.IOException {
		return this.mSrv.getImageSource(img);
	}
}
