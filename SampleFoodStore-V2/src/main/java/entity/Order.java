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
	public boolean addCuisinesInOrder(Cuisine c, int number, int isVip) {
		if (cuisines.containsKey(c)) {
			cuisines.put(c, cuisines.get(c)+number);
		}
		else {
			cuisines.put(c, number);
		}
		calTotalPrice(isVip); //重新计算订单的总金额
		return true;
	}
	
	//删除商品的方法
	public boolean removeCuisinesFromOrder(Cuisine c, int isVip) {
		cuisines.remove(c);
		calTotalPrice(isVip); //重新计算订单的总金额
		return true;
	}
	
	//统计订单的总金额
	public double calTotalPrice(int isVip) {
		double sum = 0.0;
		Set<Cuisine> keys = cuisines.keySet(); //获得键的集合
		Iterator<Cuisine> it = keys.iterator(); //获得迭代器对象
		while (it.hasNext()) {
			Cuisine i = it.next();
			if (i.getVip() == 1 && isVip == 1) {
				sum += i.getPrice()*0.95*cuisines.get(i);
			}
			else {
				sum += i.getPrice()*cuisines.get(i);
			}
		}
		sum = (double)Math.round(sum*100)/100;
		setTotalPrice(sum);// 设置订单的总金额
		return getTotalPrice();
	}
	
	public static void main(String[] args) {
		
		CuisinesDaoImpl dao = new CuisinesDaoImpl();
		
		Order o = new Order();
		o.addCuisinesInOrder(dao.getCuisineById(0), 4, 1);
		o.addCuisinesInOrder(dao.getCuisineById(3), 2, 1);
		o.addCuisinesInOrder(dao.getCuisineById(0), 1, 1);
		
		//遍历菜品的集合
		Set<Map.Entry<Cuisine, Integer>> items = o.getCuisines().entrySet();
		for (Map.Entry<Cuisine, Integer> obj : items) {
			System.out.println(obj);
		}
		
		System.out.println("购物车的总金额：" + o.getTotalPrice());
	}

}


















