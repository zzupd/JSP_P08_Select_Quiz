package pack.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConn {
	// DAO
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	// DB 접속용 메서드
	public Connection mtdDBConn() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/sampleData?";
			url += "useSSL=false&";
			url += "useUnicode=true&";
			url += "characterEncoding=UTF8&";
			url += "serverTimezone=Asia/Seoul&";
			url += "allowPublicKeyRetrieval=true";

			String uid = "root";
			String upw = "1234";

			conn = DriverManager.getConnection(url, uid, upw);
			// Select 실행 및 출력영역 시작

		} catch (ClassNotFoundException e) {
			System.out.print(e.getMessage());
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}

		return conn;

	}

	
	// 데이터 조회용 Select 처리 메서드 
	public List<DataBean> mtdSelect() {
		
		List<DataBean> list = new ArrayList<>();
		DataBean dataBean = null;

		try {
			stmt = conn.createStatement();
			String sql = "select num, goodsCode, goodsName, price, cnt ";
			sql += "from goodsList ";
			sql += "order by num desc";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dataBean = new DataBean();  // DTO 객체
				
				dataBean.setNum(rs.getInt("num"));
				dataBean.setGoodsCode(rs.getString("goodsCode"));
				dataBean.setGoodsName(rs.getString("goodsName"));
				dataBean.setPrice(rs.getInt("price"));
				dataBean.setCnt(rs.getInt("cnt"));
				
				list.add(dataBean);

			}

		} catch (SQLException e) {
			System.out.println("Select SQLE : "+ e.getMessage());
		}
		
		return list;

	}

}
