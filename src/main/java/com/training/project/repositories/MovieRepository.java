package com.training.project.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.training.project.dto.MovieGenre;
import com.training.project.repositories.entities.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer>{
	MovieEntity getByMovieName(String movieName);
	
	Optional<MovieEntity> findByMovieName(String movieName);
	
	@Transactional
	@Modifying
	@Query("delete from MovieEntity e where e.movieName=?1")
	void deleteMovieByName(String movieName);	
	
	Page<MovieEntity> getByMovieGenre(MovieGenre movieGenre, Pageable pageable);
}
