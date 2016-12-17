package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entity.Cuisine;
import entity.Order;

//业务逻辑类
public class CuisinesDaoImpl implements CuisinesDao {

	CuisineRepository cuisineRepository = new CuisineRepository();
	
	// 获得所有的菜品
	public ArrayList<Cuisine> getAllCuisines() {
		return (ArrayList<Cuisine>)cuisineRepository.cuisines;
	}
	
	// 查询对应城市的菜品
	public ArrayList<Cuisine> getCuisinesByCityName(String city) {
		ArrayList<Cuisine> list = new ArrayList<Cuisine>(); // 菜品集合
		ArrayList<Cuisine> all = getAllCuisines();
		for (Cuisine c : all) {
			if (c.getCity().equals(city)) {
				list.add(c);
			}
		}
		return list;
	}	
	
	// 根据菜名查询菜品
	public ArrayList<Cuisine> getCuisinesByName(String name) {
		ArrayList<Cuisine> list = new ArrayList<Cuisine>(); // 菜品集合
		ArrayList<Cuisine> all = getAllCuisines();
		for (Cuisine c : all) {
			if (c.getName().indexOf(name) != -1) {
				list.add(c);
			}
		}
		return list;
	}	

	// 根据菜品编号查询菜品
	public Cuisine getCuisineById(int id) {
		ArrayList<Cuisine> all = getAllCuisines();
		for (Cuisine c : all) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}	
	
	public boolean addToOrder(int id, int num, HttpServletRequest request) {
		Cuisine c = getCuisineById(id);
	
		//判断是否是第一次添加该菜品,需要给session中创建一个新的订单对象
		if(request.getSession().getAttribute("order") == null) {
			Order order = new Order();
			request.getSession().setAttribute("order", order);
		}
		Order order = (Order)request.getSession().getAttribute("order");
		if(order.addCuisinesInOrder(c, num)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public boolean deleteFromOrder(int id, HttpServletRequest request) {
		Order order = (Order)request.getSession().getAttribute("order");
	    Cuisine c = getCuisineById(id);
	    if(order.removeCuisinesFromOrder(c)) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	
	public static void main(String[] args) {
		CuisinesDaoImpl dao = new CuisinesDaoImpl();
		ArrayList<Cuisine> list = dao.getCuisinesByName("饭");
		if (list != null && list.size() > 0){
			for (Cuisine c : list) {
				System.out.println(c.getName());
			} 
		}
		
//		Cuisine c = dao.getCuisineById(4);
//		System.out.println(c.getId() + " " + c.getName() + " " + c.getDescription());
	}
		
}
