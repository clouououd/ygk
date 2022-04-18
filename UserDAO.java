package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import util.ConnectionPool;

public class UserDAO {
	// db에서 회원정보를 불러올 함수
	public UserDTO getDetail(String uid) throws NamingException, SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * FROM user WHERE uid=?";
			
			conn = ConnectionPool.get();
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, uid);
			rs = pstmt.executeQuery();
			
			rs.next();
			//String id = rs.getString(1); //uid(아이디)
			//String upw = rs.getString(2); //upw(비밀번호)
			String uname = rs.getString(3); //uname(이름)
			String ugender = rs.getString(4); //ugender(성별)
			String ubirth = rs.getString(5); //ubirth(생년월일)
			
			UserDTO userDetail = new UserDTO(uname, ugender, ubirth);
			return userDetail;
		} finally {
			if(rs != null) pstmt.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) pstmt.close();
		}
	}
}
