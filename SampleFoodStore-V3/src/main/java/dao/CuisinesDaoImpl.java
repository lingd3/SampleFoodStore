package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import entity.Cuisine;
import entity.Order;
import entity.User;
import util.DBUtil;

@Repository
public class CuisinesDaoImpl implements CuisinesDao {

	// 获得所有的菜品s
	public ArrayList<Cuisine> getAllCuisines() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Cuisine> list = new ArrayList<Cuisine>(); // 菜品集合
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from Cuisine;"; // SQL语句
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cuisine c = new Cuisine();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				c.setCity(rs.getString("city"));
				c.setPrice(rs.getDouble("price"));
				c.setPicture(rs.getString("picture"));
				c.setVip(rs.getInt("vip"));
				list.add(c);// 把一个菜品加入集合
			}
			return list; // 返回集合。
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
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
	}
	
	// 查询对应城市的菜品
	public ArrayList<Cuisine> getCuisinesByCityName(String city) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Cuisine> list = new ArrayList<Cuisine>(); // 菜品集合
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from Cuisine where city LIKE ?;"; // SQL语句
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+city+"%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cuisine c = new Cuisine();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				c.setCity(rs.getString("city"));
				c.setPrice(rs.getDouble("price"));
				c.setPicture(rs.getString("picture"));
				c.setVip(rs.getInt("vip"));
				list.add(c);// 把一个菜品加入集合
			}
			return list; // 返回集合。
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
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
	}	
	
	// 根据菜名查询菜品
	public ArrayList<Cuisine> getCuisinesByName(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Cuisine> list = new ArrayList<Cuisine>(); // 菜品集合
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from Cuisine where name LIKE ?;"; // SQL语句
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+name+"%");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cuisine c = new Cuisine();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				c.setCity(rs.getString("city"));
				c.setPrice(rs.getDouble("price"));
				c.setPicture(rs.getString("picture"));
				c.setVip(rs.getInt("vip"));
				list.add(c);// 把一个菜品加入集合
			}
			return list; // 返回集合。
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
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
	}	

	// 根据菜品编号查询菜品
	public Cuisine getCuisineById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cuisine c = new Cuisine();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from Cuisine where id = ?;"; // SQL语句
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				c.setCity(rs.getString("city"));
				c.setPrice(rs.getDouble("price"));
				c.setPicture(rs.getString("picture"));
				c.setVip(rs.getInt("vip"));
				return c;
			}
			else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
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
	}	
	
	//添加菜品带订单
	public boolean addToOrder(int id, int num, HttpServletRequest request) {
		Cuisine c = getCuisineById(id);
	
		//判断是否是第一次添加该菜品,需要给session中创建一个新的订单对象
		if(request.getSession().getAttribute("order") == null) {
			Order order = new Order();
			request.getSession().setAttribute("order", order);
		}
		Order order = (Order)request.getSession().getAttribute("order");
		User u = (User)request.getSession().getAttribute("user");
		if(u != null && order.addCuisinesInOrder(c, num, u.getVip())) {
			return true;
		}
		else {
			return false;
		}
		
	}

	//从订单删除菜品
	public boolean deleteFromOrder(int id, HttpServletRequest request) {
		Order order = (Order)request.getSession().getAttribute("order");
		User u = (User)request.getSession().getAttribute("user");
	    Cuisine c = getCuisineById(id);
	    
	    if(u != null && order.removeCuisinesFromOrder(c, u.getVip())) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	//获取最近浏览的前三条菜品信息
	public ArrayList<Cuisine> getViewList(String list) {
//		System.out.println("list:"+list);
		ArrayList<Cuisine> cuisines = new ArrayList<Cuisine>();
		int iCount = 3; //每次返回前三条记录
		if (list != null && list.length() > 0) {
		    String[] arr = list.split(",");
//		    System.out.println("arr.length="+arr.length);
		    //如果商品记录大于等于3条
		    if (arr.length >= 3) {
		    	int count = 0;
		    	for (int i = arr.length-1; i >= 0; i--) {
		    		if (count > 2) break;
		    		int id = Integer.parseInt(arr[i]), already = 0;
		    		for (int j = 0; j < cuisines.size(); j++) {
		    			if (cuisines.get(j).getId() == id) {
		    				already = 1;
		    				break;
		    			}
		    		}
		    		if (already == 0) {
		    			cuisines.add(getCuisineById(id));
		    			count++;
		    		}
		    	}
		    }
		    else {
		    	for (int i = arr.length-1; i >= 0; i--) {
		    		int id = Integer.parseInt(arr[i]), already = 0;
		    		for (int j = 0; j < cuisines.size(); j++) {
		    			if (cuisines.get(j).getId() == id) {
		    				already = 1;
		    				break;
		    			}
		    		}
		    		if (already == 0) {
		    			cuisines.add(getCuisineById(id));
		    		}
		    	}
		    }
		    return cuisines;
		}
		else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		CuisinesDaoImpl dao = new CuisinesDaoImpl();
		ArrayList<Cuisine> list = dao.getViewList("1,2,3,4,5");
		if (list != null && list.size() > 0){
			for (Cuisine c : list) {
				System.out.println(c.getName());
			} 
		}
		
	}
	
}


