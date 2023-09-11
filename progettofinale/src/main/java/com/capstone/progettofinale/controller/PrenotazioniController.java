package com.capstone.progettofinale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.progettofinale.model.Acquisto;
import com.capstone.progettofinale.model.Prenotazione;
import com.capstone.progettofinale.payload.AcquistoPayload;
import com.capstone.progettofinale.payload.PrenotazionePayload;
import com.capstone.progettofinale.service.PrenotazioneService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

	@Autowired
	private PrenotazioneService pSrv;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<PrenotazionePayload>> getPrenotazioni() {
		List<Prenotazione> lista = pSrv.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ofNullable(lista.stream().map(PrenotazionePayload::new).toList());
		}
	}

	@GetMapping("/saldo")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<PrenotazionePayload>> getSaldo() {
		List<Prenotazione> lista = pSrv.findDaPagare();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ofNullable(lista.stream().map(PrenotazionePayload::new).toList());
		}
	}

	@GetMapping("/acquisti")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<AcquistoPayload>> getRiepilogoAcquisti() {
		List<Acquisto> lista = pSrv.riepilogoAcquisti();
		return ResponseEntity.ofNullable(lista.stream().map(AcquistoPayload::new).toList());
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<PrenotazionePayload> prenota(@RequestBody PrenotazionePayload pp) {
		Prenotazione p = pSrv.save(pp);
		return ResponseEntity.ofNullable(new PrenotazionePayload(p));
	}

	@PostMapping("/acquisti/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<AcquistoPayload> acquista(@PathVariable Long id) {
		Acquisto a = pSrv.acquistaPrenotazione(id);
		return ResponseEntity.ofNullable(new AcquistoPayload(a));
	}

}
