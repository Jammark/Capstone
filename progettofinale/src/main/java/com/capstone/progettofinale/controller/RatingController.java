package com.capstone.progettofinale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.progettofinale.model.Rating;
import com.capstone.progettofinale.payload.RatingPayload;
import com.capstone.progettofinale.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	private RatingService srv;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<RatingPayload> rate(@RequestBody RatingPayload rp) {
		Rating r = srv.save(rp);
		return ResponseEntity.ofNullable(new RatingPayload(r));
	}
	/*
	 * @PutMapping("{id}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public ResponseEntity<RatingPayload>
	 * updateRate(@PathVariable Long id, @RequestBody RatingPayload rp) {
	 * rp.setId(id); Rating r = srv.update(rp); return ResponseEntity.ofNullable(new
	 * RatingPayload(r)); }
	 */
}
