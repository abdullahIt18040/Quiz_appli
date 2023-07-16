package com.quiz_appli.quiz_appli.repository;


import com.quiz_appli.quiz_appli.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {
	
}
