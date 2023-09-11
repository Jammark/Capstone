package com.capstone.progettofinale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.progettofinale.model.Appartamento;
import com.capstone.progettofinale.model.Hotel;
import com.capstone.progettofinale.payload.AppartamentoPayload;
import com.capstone.progettofinale.payload.HotelPayload;
import com.capstone.progettofinale.service.AlloggioService;
import com.capstone.progettofinale.service.RatingService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/alloggi")
@Slf4j
public class AlloggioController {

	@Autowired
	private AlloggioService aSrv;

	@Autowired
	private RatingService rSrv;

	@GetMapping("/hotels")
	public ResponseEntity<List<HotelPayload>> getAllHotels() {
		List<Hotel> lista = aSrv.findAllHotel();
		List<HotelPayload> body = lista.stream().map(HotelPayload::new).toList();
		rSrv.setRatings(body);
		return ResponseEntity.ok(body);
	}

	@GetMapping("/hotels/{id}")
	public ResponseEntity<HotelPayload> getHotelById(@PathVariable Long id) {
		try {
			Hotel h = aSrv.findHotelById(id);
			HotelPayload body = new HotelPayload(h);
			rSrv.setRatings(List.of(body));
			return ResponseEntity.ok(body);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/appartamenti")
	public ResponseEntity<List<AppartamentoPayload>> getAllAppartamenti() {
		List<Appartamento> lista = aSrv.findAllAppartamenti();
		List<AppartamentoPayload> body = lista.stream().map(AppartamentoPayload::new).toList();
		rSrv.setRatings(body);
		return ResponseEntity.ok(body);
	}

	@GetMapping("/appartamenti/{id}")
	public ResponseEntity<AppartamentoPayload> getAppartamentoById(@PathVariable Long id) {
		try {
			Appartamento c = aSrv.findAppartamentoById(id);
			AppartamentoPayload body = new AppartamentoPayload(c);
			rSrv.setRatings(List.of(body));
			return ResponseEntity.ok(body);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/hotels")
	public ResponseEntity<HotelPayload> createCittà(@Valid @RequestBody HotelPayload cp) {

		Hotel c = aSrv.saveHotel(cp);
		HotelPayload body = new HotelPayload(c);
		rSrv.setRatings(List.of(body));
		return new ResponseEntity<HotelPayload>(body, HttpStatus.CREATED);

	}

	@PostMapping("/appartamenti")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AppartamentoPayload> creteDestination(@Valid @RequestBody AppartamentoPayload dp) {
		Appartamento d = aSrv.saveAppartamento(dp);
		AppartamentoPayload body = new AppartamentoPayload(d);
		rSrv.setRatings(List.of(body));
		return ResponseEntity.ofNullable(body);
	}

	@PutMapping("/hotels/{id}")
	public ResponseEntity<HotelPayload> updateHotel(@PathVariable Long id, @RequestBody HotelPayload hp) {
		hp.setId(id);
		Hotel h = this.aSrv.updateHotel(hp);
		HotelPayload body = new HotelPayload(h);
		rSrv.setRatings(List.of(body));
		return new ResponseEntity<HotelPayload>(body, HttpStatus.OK);
	}

	@PutMapping("/appartamenti/{id}")
	public ResponseEntity<AppartamentoPayload> updateAppartamento(@PathVariable Long id,
			@RequestBody AppartamentoPayload ap) {
		ap.setId(id);
		Appartamento a = this.aSrv.updateAppartamento(ap);
		AppartamentoPayload body = new AppartamentoPayload(a);
		rSrv.setRatings(List.of(body));
		return new ResponseEntity<AppartamentoPayload>(body, HttpStatus.OK);
	}

	@DeleteMapping("hotels/{hotelId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteHotel(@PathVariable Long hotelId) {
		this.aSrv.deleteHotel(hotelId);
		return new ResponseEntity<String>("Rimosso hotel cond id: " + hotelId, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("appartamenti/{appartamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteCity(@PathVariable Long appartamentoId) {
		this.aSrv.deleteAppartamento(appartamentoId);
		return new ResponseEntity<String>("Rimossa città cond id: " + appartamentoId, HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/image/{img}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody byte[] getImage(@PathVariable String img) throws java.io.IOException {

		return this.aSrv.getImageSource(img);
	}
}
