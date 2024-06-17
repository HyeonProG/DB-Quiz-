package com.tenco.quiz.ver3;

import java.sql.SQLException;
import java.util.List;

public interface QuizRepository {

	int addQuizQuestion(String question, String answer) throws SQLException;

	List<QuizDTO> viewQuizQuestion() throws SQLException;

	QuizDTO playQuizGame() throws SQLException;

}
