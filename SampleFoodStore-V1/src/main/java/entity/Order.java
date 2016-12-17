package entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import dao.CuisinesDaoImpl;

public class Order {

	// 购买商品的集合
	private HashMap<Cuisine, Integer> cuisines;

	// 购物车的总金额
	private double totalPrice;

	public Order() {
		cuisines = new HashMap<Cuisine, Integer>();
		totalPrice = 0.0;
	}

	public HashMap<Cuisine, Integer> getCuisines() {
		return cuisines;
	}

	public void setCuisines(HashMap<Cuisine, Integer> cuisines) {
		this.cuisines = cuisines;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	//添加菜品进订单的方法
	public boolean addCuisinesInOrder(Cuisine c, int number) {
		if (cuisines.containsKey(c)) {
			cuisines.put(c, cuisines.get(c)+number);
		}
		else {
			cuisines.put(c, number);
		}
		calTotalPrice(); //重新计算订单的总金额
		return true;
	}
	
	//删除商品的方法
	public boolean removeCuisinesFromOrder(Cuisine c) {
		cuisines.remove(c);
		calTotalPrice(); //重新计算订单的总金额
		return true;
	}
	
	//统计订单的总金额
	public double calTotalPrice() {
		double sum = 0.0;
		Set<Cuisine> keys = cuisines.keySet(); //获得键的集合
		Iterator<Cuisine> it = keys.iterator(); //获得迭代器对象
		while (it.hasNext()) {
			Cuisine i = it.next();
			sum += i.getPrice()*cuisines.get(i);
		}
		setTotalPrice(sum);// 设置订单的总金额
		return getTotalPrice();
	}
	
	public static void main(String[] args) {
		
		CuisinesDaoImpl dao = new CuisinesDaoImpl();
		
		Order o = new Order();
		o.addCuisinesInOrder(dao.getCuisineById(0), 4);
		o.addCuisinesInOrder(dao.getCuisineById(3), 2);
		o.addCuisinesInOrder(dao.getCuisineById(0), 1);
		
		//遍历菜品的集合
		Set<Map.Entry<Cuisine, Integer>> items = o.getCuisines().entrySet();
		for (Map.Entry<Cuisine, Integer> obj : items) {
			System.out.println(obj);
		}
		
		System.out.println("订单的总金额：" + o.getTotalPrice());
	}

}
