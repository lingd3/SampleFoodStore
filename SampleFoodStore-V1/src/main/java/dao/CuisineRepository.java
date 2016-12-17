package dao;

import java.util.ArrayList;
import java.util.List;

import entity.Cuisine;

public class CuisineRepository {
	
	public static List<Cuisine> cuisines = new ArrayList<Cuisine>();
	
	public CuisineRepository() {
		Cuisine c0 = new Cuisine(0, "蛋炒饭", "香气怡人，葱花的清香加上鸡蛋与米饭的混合香气，让人有一种忍不住想大饱口福的冲动。", "广州", 10, "000.jpg", 1);
		Cuisine c1 = new Cuisine(1, "云吞面", "饱满的猪肉和韭黄散发出一阵阵香喷喷的气味，又大又弹牙。", "深圳", 8, "001.jpg", 0);
		Cuisine c2 = new Cuisine(2, "汉堡包", "酸黄瓜，牛肉饼，加上芝士，大大的满足。", "深圳", 20, "002.jpg", 1);
		Cuisine c3 = new Cuisine(3, "小龙虾", "鲜嫩的虾肉配上大葱，喷鼻的鲜香让人无法抗拒。", "深圳", 400, "003.jpg", 0);
		Cuisine c4 = new Cuisine(4, "辣子鸡", "麻辣鲜香，色香味俱全，让人垂涎欲滴，赞不绝口。", "深圳", 20, "004.jpg", 1);
		Cuisine c5 = new Cuisine(5, "烧鸭饭", "红色的烧鸭配上白饭和青菜，简单而美味，充满烧腊香味。", "广州", 15, "005.jpg", 0);
		Cuisine c6 = new Cuisine(6, "过桥米线", "米线筋道而且口感很好，味美汤鲜，香味溢满整条街。", "广州", 10, "006.jpg", 0);
		Cuisine c7 = new Cuisine(7, "鸡扒饭", "西兰花，胡萝卜，加上带浓浓蒜香的鸡扒，营养丰富，值得尝试。", "广州", 13, "007.jpg", 1);
		Cuisine c8 = new Cuisine(8, "焖鸡", "味道美妙，肉质鲜美嫩滑，色、香、味丰富。", "深圳", 24, "008.jpg", 0);
		Cuisine c9 = new Cuisine(9, "烤鸭", "色泽红艳，肉质细嫩，味道醇厚，肥而不腻。", "深圳", 18, "009.jpg", 0);
		Cuisine c10 = new Cuisine(10, "艇仔粥", "料多而不杂，爽脆软滑，鲜甜香美，清甜绵滑，是那种熟悉的温暖的味道。", "广州", 17, "010.jpg", 1);
		Cuisine c11 = new Cuisine(11, "肠粉", "粉粉嫩嫩，晶莹剔透，粉皮白如雪花、薄如蝉翼，吃起来鲜香满口、细腻爽滑。", "广州", 8, "011.jpg", 0);
		Cuisine c12 = new Cuisine(12, "奥尔良鸡翅", "稍甜的鸡翅散发着淡淡的奶香，鲜嫩有劲。", "深圳", 16, "012.jpg", 0);
		Cuisine c13 = new Cuisine(13, "意面", "爽滑酥嫩，肉汁四溢，口感饱满，回味悠长，软嫩滑爽。 ", "广州", 23, "013.jpg", 1);
		Cuisine c14 = new Cuisine(14, "番茄炒鸡蛋", "美味营养兼备，其味酸甜可口，酥滑兼贻，番茄的酸味配上鸡蛋的香味，让人无比享受。", "深圳", 20, "014.jpg", 1);
		Cuisine c15 = new Cuisine(15, "水煮肉片", "香油，香菜，芝麻，花生，辣椒，加上鲜嫩的肉片，让人回味无穷。", "广州", 19, "015.jpg", 0);
		cuisines.add(c0);
		cuisines.add(c1);
		cuisines.add(c2);
		cuisines.add(c3);
		cuisines.add(c4);
		cuisines.add(c5);
		cuisines.add(c6);
		cuisines.add(c7);
		cuisines.add(c8);
		cuisines.add(c9);
		cuisines.add(c10);
		cuisines.add(c11);
		cuisines.add(c12);
		cuisines.add(c13);
		cuisines.add(c14);
		cuisines.add(c15);
	}
	
	
}

