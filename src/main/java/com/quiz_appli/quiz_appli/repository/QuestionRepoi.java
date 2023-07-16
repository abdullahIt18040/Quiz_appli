package com.quiz_appli.quiz_appli.repository;

import com.quiz_appli.quiz_appli.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepoi extends JpaRepository<Question,Integer> {
}
