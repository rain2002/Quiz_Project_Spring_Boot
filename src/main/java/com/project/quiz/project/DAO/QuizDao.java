package com.project.quiz.project.DAO;

import com.project.quiz.project.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
