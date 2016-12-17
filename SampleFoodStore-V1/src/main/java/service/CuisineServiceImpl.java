package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.CuisinesDaoImpl;
import entity.Cuisine;

public class CuisineServiceImpl implements CuisineService {
	
	private CuisinesDaoImpl cuisineDao;
	
	public CuisineServiceImpl() {
		cuisineDao = new CuisinesDaoImpl();
	}
	
	// 获得所有的菜品
	public ArrayList<Cuisine> getAllCuisines() {
		return cuisineDao.getAllCuisines();
	}
		
	// 查询对应城市的菜品
	public ArrayList<Cuisine> getCuisinesByCityName(String city) {
		return cuisineDao.getCuisinesByCityName(city);
	}
		
	// 根据菜名查询菜品
	public ArrayList<Cuisine> getCuisinesByName(String name) {
		return cuisineDao.getCuisinesByName(name);
	}
		
	// 根据菜品编号查询菜品
	public Cuisine getCuisineById(int id) {
		return cuisineDao.getCuisineById(id);
	}
	
	//将菜品加入订单
	public boolean addToOrder(int id, int num, HttpServletRequest request) {
		return cuisineDao.addToOrder(id, num, request);
	}
		
	//将菜品从订单删除
	public boolean deleteFromOrder(int id, HttpServletRequest request) {
		return cuisineDao.deleteFromOrder(id, request);
	}
	
}
