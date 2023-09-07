package com.capstone.progettofinale.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.model.Città;
import com.capstone.progettofinale.model.Destinazione;
import com.capstone.progettofinale.model.MetaTuristica;
import com.capstone.progettofinale.payload.CittàPayload;
import com.capstone.progettofinale.payload.DestinazionePayload;
import com.capstone.progettofinale.repository.CittàRepository;
import com.capstone.progettofinale.repository.DestinazioneRepository;
import com.capstone.progettofinale.repository.MetaRepository;

@Service
public class MetaService {

	@Autowired
	private CittàRepository cRepo;

	@Autowired
	private DestinazioneRepository dRepo;

	@Autowired
	private MetaRepository mRepo;

	public MetaTuristica findById(Long id) {
		return this.mRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id meta turistica non valido: " + id));
	}

	public Città findCittàById(Long id) {
		return cRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Id città non valido: " + id));
	}

	public Destinazione findDestinazioneById(Long id) {
		return dRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Id destinazione non valido: " + id));
	}

	public List<Città> findAllCittà() {
		return cRepo.findAll();
	}

	public List<Destinazione> findAllDestinazioni() {
		return dRepo.findAll();
	}

	public void deleteCittà(Long id) {
		cRepo.delete(this.findCittàById(id));
	}

	public Città saveCittà(Città c) {
		return this.cRepo.save(c);
	}

	public Destinazione saveDestinazione(Destinazione d) {
		return this.dRepo.save(d);
	}

	public List<Città> findByDestinazione(Long id) {
		return this.cRepo.findByDestinazioneId(id);
	}

	public Città updateCittà(CittàPayload cp) {
		Città found = this.findCittàById(cp.getId());
		found.setDescrizione(cp.getDescrizione());
		found.setNome(cp.getNome());
		return this.saveCittà(found);
	}

	public Destinazione updateDestinazione(DestinazionePayload dp) {
		Destinazione found = this.findDestinazioneById(dp.getId());
		found.setContenutoPrincipale(dp.getContenutoPrincipale());
		found.setContenutoSecondario(dp.getContenutoSecondario());
		return this.saveDestinazione(found);
	}

	public Destinazione removeCittà(CittàPayload cp) {
		if (cp.getDestinazione() != null && cp.getDestinazione().getId() != null) {
			Destinazione dest = this.findDestinazioneById(cp.getDestinazione().getId());
			dest.setCittà(
					Set.of(dest.getCittà().stream().filter(d -> !d.getId().equals(cp.getId())).toArray(Città[]::new)));
			return this.saveDestinazione(dest);
		} else {
			throw new IllegalArgumentException("campo destinazione non valido nel body: " + cp);
		}
	}

	public Destinazione aggiungiCittà(CittàPayload cp) {
		if (cp.getDestinazione() != null && cp.getDestinazione().getId() != null) {
			Destinazione dest = this.findDestinazioneById(cp.getDestinazione().getId());
			dest.getCittà().add(this.findCittàById(cp.getId()));
			return this.saveDestinazione(dest);
		} else {
			throw new IllegalArgumentException("campo destinazione non valido nel body: " + cp);
		}
	}

}
