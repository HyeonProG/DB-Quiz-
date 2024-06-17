package com.tenco.quiz.ver3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
/*
 * 커넥션 풀을 활용하는 예제로 수정 해보자.
 * lib 설정 - HikariCP-5.1.0.jar
 */
public class DBConnectionManager {
	
	private static HikariDataSource dataSource;

	private static final String URL = "jdbc:mysql://localhost:3306/quizdb?serverTimezone=Asia/Seoul";
	private static final String USER = "root";
	private static final String PASSWORD = "asd123";

	// static{} 블록 - 정적 초기화 블록
	// 클래스가 처음 로드될 때 한번 실행 된다.
	// 정적 변수의 초기화나 복잡한 초기화 작업을 수행할 때 사용
	// static{} 블록안에 예외를 던질 수도 있다.
	static {

		// HikariCP를 사용하기 위한 설정이 필요하다.
		// HikariConfig를 제공을 해줘서 이 클래스를 활용해 설정을 상세히 할 수 있다.
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		
		config.setMaximumPoolSize(10); // 최대 연결 수 설정 / 보통 10 사용
		
		dataSource = new HikariDataSource(config);

	} // end of static
	
	public static Connection getConnection() throws SQLException {
		System.out.println("HikariCP를 사용한 Data Source 활용");
		return dataSource.getConnection();
	}
	
	// 테스트 코드
	public static void main(String[] args) {
		try {
			Connection conn = DBConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end of main

	// 정적 메서드(함수) Connection 객체를 리턴하는 함수를 만들어 보자.
	// 기본 JDBC 드라이버 사용 버전
//	public static Connection getConnection() throws SQLException {
//		return DriverManager.getConnection(URL, USER, PASSWORD);
//	}

} // end of class
