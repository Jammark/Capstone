package com.capstone.progettofinale.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.auth.BadRequestException;
import com.capstone.progettofinale.model.Città;
import com.capstone.progettofinale.model.Destinazione;
import com.capstone.progettofinale.model.MetaTuristica;
import com.capstone.progettofinale.payload.CittàPayload;
import com.capstone.progettofinale.payload.DestinazionePayload;
import com.capstone.progettofinale.repository.CittàRepository;
import com.capstone.progettofinale.repository.DestinazioneRepository;
import com.capstone.progettofinale.repository.MetaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MetaService extends AbstractService {


	@Autowired
	private CittàRepository cRepo;

	@Autowired
	private DestinazioneRepository dRepo;

	@Autowired
	private MetaRepository mRepo;

	public MetaService() {
		super("mete");
	}

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

	public void deleteDestinazione(Long id) {
		dRepo.delete(this.findDestinazioneById(id));
	}

	public Città saveCittà(CittàPayload cp) throws java.io.IOException {
		if (this.cRepo.findByNome(cp.getNome()).isEmpty()) {
			Città c = new Città(cp.getNome(), cp.getDescrizione(), cp.getImgUrl(),
					cp.getDestinazione() != null ? this.findDestinazioneById(cp.getDestinazione().getId()) : null);
			if (c.getUrlImmagine() != null && !c.getUrlImmagine().isEmpty()) {
				String path = c.getNome() + ".jpg";
				storeImg(c.getUrlImmagine(), path);
				c.setUrlImmagine(getImagePath(path));
			}
			return this.cRepo.save(c);
		} else {
			throw new BadRequestException("Nome meta turistica già utilizzato: " + cp.getNome());
		}
	}



	public Destinazione saveDestinazione(DestinazionePayload dp) {
		if (this.mRepo.findByNome(dp.getNome()).isEmpty()) {
			Destinazione d = new Destinazione(dp.getNome(), dp.getDescrizione(), dp.getImgUrl(),
					dp.getContenutoPrincipale(), dp.getContenutoSecondario());
			if (d.getUrlImmagine() != null && !d.getUrlImmagine().isEmpty()) {
				String path = d.getNome() + ".jpg";
				storeImg(d.getUrlImmagine(), path);
				d.setUrlImmagine(getImagePath(path));
			}
			return this.dRepo.save(d);
		} else {
			throw new BadRequestException("Nome meta turistica già utilizzato: " + dp.getNome());
		}
	}

	public List<Città> findByDestinazione(Long id) {
		return this.cRepo.findByDestinazioneId(id);
	}

	public Città updateCittà(CittàPayload cp) {
		Città found = this.findCittàById(cp.getId());
		found.setDescrizione(Optional.ofNullable(cp.getDescrizione()).orElse(found.getDescrizione()));
		found.setNome(Optional.ofNullable(cp.getNome()).orElse(found.getNome()));
		if (cp.getImgUrl() != null && !cp.getImgUrl().isEmpty()) {
			String path = found.getNome() + ".jpg";
			storeImg(cp.getImgUrl(), path);
			found.setUrlImmagine(getImagePath(path));
		}
		return this.cRepo.save(found);
	}

	public Destinazione updateDestinazione(DestinazionePayload dp) {
		Destinazione found = this.findDestinazioneById(dp.getId());
		found.setNome(Optional.ofNullable(dp.getNome()).orElse(found.getNome()));
		found.setDescrizione(Optional.ofNullable(dp.getDescrizione()).orElse(found.getDescrizione()));
		found.setContenutoPrincipale(
				Optional.ofNullable(dp.getContenutoPrincipale()).orElse(found.getContenutoPrincipale()));
		found.setContenutoSecondario(
				Optional.ofNullable(dp.getContenutoSecondario()).orElse(found.getContenutoSecondario()));
		if (dp.getImgUrl() != null && !dp.getImgUrl().isEmpty()) {
			String path = found.getNome() + ".jpg";
			storeImg(dp.getImgUrl(), path);
			found.setUrlImmagine(getImagePath(path));
		}
		return this.dRepo.save(found);
	}

	public Destinazione removeCittà(CittàPayload cp) {
		if (cp.getDestinazione() != null && cp.getDestinazione().getId() != null) {
			Destinazione dest = this.findDestinazioneById(cp.getDestinazione().getId());
			dest.setCittà(
					Set.of(dest.getCittà().stream().filter(d -> !d.getId().equals(cp.getId())).toArray(Città[]::new)));
			return this.dRepo.save(dest);
		} else {
			throw new IllegalArgumentException("campo destinazione non valido nel body: " + cp);
		}
	}

	public Destinazione aggiungiCittà(CittàPayload cp, Long destId) {
		if (destId != null) {
			Destinazione dest = this.findDestinazioneById(destId);
			Città c = this.findCittàById(cp.getId());
			c.setDestinazione(dest);
			dest.getCittà().add(this.cRepo.save(c));

			return this.dRepo.save(dest);
		} else {
			throw new IllegalArgumentException("campo destinazione non valido: " + cp);
		}
	}


}
