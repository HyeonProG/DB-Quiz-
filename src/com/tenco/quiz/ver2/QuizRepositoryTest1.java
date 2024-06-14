package com.tenco.quiz.ver2;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class QuizRepositoryTest1 {

	public static void main(String[] args) {

		// 메서드 호출해서 실행 확인, 디버깅 확인
		// QuizRepository 구현 클래스 테스트

		// 실행의 흐름 만들어 보기
		QuizRepositoryImpl quizRepositoryImpl = new QuizRepositoryImpl();
		Scanner scanner = new Scanner(System.in);

		try {

			while (true) {
				System.out.println();
				System.out.println("-------------------------------");
				System.out.println("1. 퀴즈 문제 추가");
				System.out.println("2. 퀴즈 문제 조회");
				System.out.println("3. 퀴즈 게임 시작");
				System.out.println("4. 종료");
				System.out.print("옵션을 선택하세요 : ");

				int choice = scanner.nextInt();

				if (choice == 1) {
					System.out.println("퀴즈 문제 추가");
					System.out.println("퀴즈 문제를 입력하세요");
					String question = scanner.next();
					System.out.println("퀴즈 정답을 입력하세요");
					String answer = scanner.next();
					quizRepositoryImpl.addQuizQuestion(question, answer);
				} else if (choice == 2) {
					// 퀴즈 내역 출력
					System.out.println("퀴즈 문제 조회");
					List<QuizDTO> dto = quizRepositoryImpl.viewQuizQuestion();
					for (QuizDTO quizDTO : dto) {
						System.out.println(quizDTO);
					}

				} else if (choice == 3) {
					System.out.println("퀴즈 게임 시작");
					QuizDTO dto = quizRepositoryImpl.playQuizGame();
					String question = dto.getQuestion();
					String answer = dto.getAnswer();
					System.out.println(question);
					String userAnswer = scanner.next();
					if (userAnswer.equalsIgnoreCase(answer)) {
						System.out.println("정답입니다!");
					} else {
						System.out.println("오답입니다.");
						System.out.println("정답 : " + answer);
					}
				} else if (choice == 4) {
					System.out.println("프로그램 종료");
					break;
				} else {
					System.out.println("다시 선택하세요");
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}


	} // end of main

} // end of class
