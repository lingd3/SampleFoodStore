package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import dao.CuisinesDao;
import dao.CuisinesDaoImpl;
import entity.Cuisine;

@Service
public class CuisineServiceImpl implements CuisineService {
	
	@Autowired
	private CuisinesDao cuisinesDao;

	// 获得所有的菜品
	@Cacheable(value="CuisineDetail")
	public ArrayList<Cuisine> getAllCuisines() {
		return cuisinesDao.getAllCuisines();
	}
		
	// 查询对应城市的菜品
	public ArrayList<Cuisine> getCuisinesByCityName(String city) {
		return cuisinesDao.getCuisinesByCityName(city);
	}
		
	// 根据菜名查询菜品
	public ArrayList<Cuisine> getCuisinesByName(String name) {
		System.out.println("Inside ...");
		return cuisinesDao.getCuisinesByName(name);
	}
		
	// 根据菜品编号查询菜品
	public Cuisine getCuisineById(int id) {
		return cuisinesDao.getCuisineById(id);
	}
	
	//将菜品加入订单
	public boolean addToOrder(int id, int num, HttpServletRequest request) {
		return cuisinesDao.addToOrder(id, num, request);
	}
		
	//将菜品从订单删除
	public boolean deleteFromOrder(int id, HttpServletRequest request) {
		return cuisinesDao.deleteFromOrder(id, request);
	} 
	
	public static void main(String[] args) {
		CuisineServiceImpl cuisineService = new CuisineServiceImpl();
		ArrayList<Cuisine> list = cuisineService.getCuisinesByName("鸡");
		for (Cuisine c : list) {
			System.out.println(c.getName());
		}
		
	}
}















