package com.bitacademy.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.mysite.vo.BoardVo;
@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. 연결하기
			String url = "jdbc:mysql://localhost :3306/webdb?characterEncoding=utf8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("error" + e);
		}

		return conn;
	}
	public long findByParent(long target) {
		long res=-1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql ="select g_no from board where no=?;";

			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩

			// 5. SQL문 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 가져오기
			if (rs.next()) {
				res = rs.getLong(1);
			}

			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return res;
	}
	public long getAuto() {
		long res=-1;
		res=sqlSession.selectOne("getAuto");
		return res;
	}
	
		
	
	public boolean newinsert(BoardVo vo) {
		int count = sqlSession.insert("Insert", vo);
		return count==1;
	}
	public boolean before(BoardVo vo) {
		sqlSession.update("before", vo);
		return true;
	}
	public boolean reinsert(BoardVo vo) {
		this.before(vo);
		int count=sqlSession.insert("Insert", vo);
		return count==1;
	}
	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql ="select no, title, writer, contents,g_no,depth,reg_date from board order by g_no desc ,depth asc , no desc;";

			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩

			// 5. SQL문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String contents = rs.getString(4);
				String gno=rs.getString(5);
				String depth = rs.getString(6);
				String date = rs.getString(7);

				BoardVo vo = new BoardVo();
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setNo(no);
				vo.setContents(contents);
				vo.setReg_date(date);
				vo.setDepth(Integer.parseInt(depth));
				vo.setG_no(Long.parseLong(gno));

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	public BoardVo findByNo(long no) {
		return sqlSession.selectOne("findByNo", no);
	}
	public boolean deleteV1(long no) {
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = " delete" + "   from board" + " where no=?";

			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩
			pstmt.setLong(1, no);
	

			// 5. SQL문 실행
			count = pstmt.executeUpdate();

			// 6. 결과
			return count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
		
	}
	public boolean deleteV2(long no) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = this.getConnection();
			PreparedStatement pstmt = null;
			String sql = "update board set  title=?, contents=? where no=?; ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, new BoardVo().getDel());
			pstmt.setString(2, "");
			pstmt.setLong(3, no);
			
			
			int cnt = pstmt.executeUpdate();

			return cnt == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error: " + e);
		} finally {
			try {
				conn.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
		
	}
	public List<BoardVo> findPage(int page) {
		long start=(long)10*(page-1);
		return sqlSession.selectList("findPage", start);
		
	}
	public long getCount() {
		return sqlSession.selectOne("getCount");
		
	}
	public long getGorderRe(long parent) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql ="select max(g_order) from board where  parent=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, parent);
			// 4. 바인딩

			// 5. SQL문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			
			if(rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return 0l;
	}
	public long getGorderByP(long no) {
		return sqlSession.selectOne("getGorderByParent",no);
	}
	
	public long getGorder(long gno) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql ="select max(g_order) from board where g_no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, gno);
			// 4. 바인딩

			// 5. SQL문 실행
			rs = pstmt.executeQuery();

			// 6. 데이터 가져오기
			
			if(rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return 0l;
	}

}
