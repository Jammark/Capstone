package com.capstone.progettofinale.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.progettofinale.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	public List<Rating> findByAlloggioId(Long id);

	public List<Rating> findByUserIdAndAlloggioIdIn(Long userId, Set<Long> ids);

	Optional<Rating> findByUserIdAndAlloggioId(Long userId, Long alloggioId);

}
