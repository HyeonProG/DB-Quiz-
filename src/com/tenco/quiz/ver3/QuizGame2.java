package com.tenco.quiz.ver3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QuizGame2 {

	public static void main(String[] args) {

		try (Connection conn = DBConnectionManager.getConnection();
				Scanner scanner = new Scanner(System.in)) {

			while (true) {
				System.out.println();
				System.out.println("-------------------------------");
				System.out.println("1. 퀴즈 문제 추가");
				System.out.println("2. 퀴즈 문제 조회");
				System.out.println("3. 퀴즈 게임 시작");
				System.out.println("4. 종료");
				System.out.print("옵션을 선택하세요 : ");

				int choice = scanner.nextInt(); // 블로킹

				if (choice == 1) {
					// 퀴즈 문제 추가 --> 함수로 만들기
					addQuizQuestion(conn, scanner);
				} else if (choice == 2) {
					// 퀴즈 문제 조회 --> 함수로 만들기
					viewQuizQuestion(conn);
				} else if (choice == 3) {
					// 퀴즈 게임 시작 --> 함수로 만들기
					playQuizGame(conn, scanner);
				} else if (choice == 4) {
					System.out.println("시스템 종료");
					break;
				} else {
					System.out.println("다시 선택하세요.");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	} // end of main

	// 퀴즈 문제 추가 함수 만들기
	// 사용자에게 퀴즈와 답을 입력 받아야 한다.
	// Connection 활용해서 query를 날려야 한다.
	private static void addQuizQuestion(Connection conn, Scanner scanner) {
		System.out.println("퀴즈 문제를 입력하세요: ");
		scanner.nextLine();
		String question = scanner.nextLine();
		System.out.println("퀴즈 정답을 입력하세요: ");
		String answer = scanner.nextLine();

		String sql = " insert into quiz(question, answer) values(?, ?) ";
		try (PreparedStatement psmt = conn.prepareStatement(sql)) {
			psmt.setString(1, question);
			psmt.setString(2, answer);
			int rowsInsertedCount = psmt.executeUpdate();
			System.out.println("추가된 행의 개수 : " + rowsInsertedCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // end of addQuizQuestion

	private static void viewQuizQuestion(Connection conn) {
		String sql = " select * from quiz ";
		try (PreparedStatement psmt = conn.prepareStatement(sql)) {
			ResultSet resultSet = psmt.executeQuery();
			while (resultSet.next()) {
				System.out.println("문제 ID : " + resultSet.getInt("id"));
				System.out.println("문제 : " + resultSet.getString("question"));
				System.out.println("문제 정답 : " + resultSet.getString("answer"));
				if (!resultSet.isLast()) {
					System.out.println("-------------------------------");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of viewQuizQuestion

	private static void playQuizGame(Connection conn, Scanner scanner) {
		String sql = " select * from quiz order by rand() limit 1; ";

		try (PreparedStatement psmt = conn.prepareStatement(sql)) {
			ResultSet rs = psmt.executeQuery();

			if (rs.next()) {
				String question = rs.getString("question");
				String answer = rs.getString("answer");

				System.out.println("퀴즈 문제 : " + question);
				// 버그 처리
				scanner.nextLine();
				System.out.print("정답 : ");
				String userAnswer = scanner.nextLine();

				if (userAnswer.equalsIgnoreCase(answer)) {
					System.out.println("정답입니다!");
				} else {
					System.out.println("오답입니다.");
					System.out.println("퀴즈 정답 : " + answer);
				}
			} else {
				System.out.println("아직 퀴즈 문제를 만들고 있습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // end of playQuizGame

} // end of class
