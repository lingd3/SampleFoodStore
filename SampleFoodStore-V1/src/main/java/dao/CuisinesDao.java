package dao;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entity.Cuisine;

public interface CuisinesDao {
	
	// 获得所有的菜品
	public ArrayList<Cuisine> getAllCuisines();
	
	// 查询对应城市的菜品
	public ArrayList<Cuisine> getCuisinesByCityName(String city);
	
	// 根据菜名查询菜品
	public ArrayList<Cuisine> getCuisinesByName(String name);
	
	// 根据菜品编号查询菜品
	public Cuisine getCuisineById(int id);	

	//加入订单
	public boolean addToOrder(int id, int num, HttpServletRequest request);

	//删除菜品
	public boolean deleteFromOrder(int id, HttpServletRequest request);
}
