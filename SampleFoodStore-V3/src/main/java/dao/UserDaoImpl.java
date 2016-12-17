package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import entity.Cuisine;
import entity.User;
import util.DBUtil;

@Repository
public class UserDaoImpl implements UserDao {
	
	public User exist(String name, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from User u where u.username=? and u.password=?;"; // SQL语句
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, password);
			rs = stmt.executeQuery();
			if (rs.next()) {
				User u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setVip(rs.getInt("vip"));
				return u;
			}
			else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 释放数据集对象
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			// 释放语句对象
			if (stmt != null) {
				try {
					stmt.close();
					stmt = null;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		UserDaoImpl u = new UserDaoImpl();
		System.out.println(u.exist("123","qweqwe"));
	}
}
