package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import entity.Cuisine;
import entity.User;
import service.CuisineService;
import service.CuisineServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import util.MD5Util;
import net.sf.ehcache.CacheManager;

@Controller
public class CuisineController {
	
	@Autowired
	private CuisineService cuisineService;
	@Autowired
	private UserService userService; 
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping(value={"/", "/index"})
    public String index(@RequestParam(value="city", required=false, defaultValue="广州") String city, Model model, HttpServletRequest request)
    		throws IOException {
		
		if(request.getSession().getAttribute("order") != null)  {
			request.getSession().removeAttribute("order");
		}
		
		city = new String(city.getBytes("iso8859-1"), "UTF-8");
		
		if (cuisineService.getCuisinesByCityName(city).size() == 0) {
			model.addAttribute("cuisines", cuisineService.getCuisinesByCityName("广州"));
		}
		else {
			model.addAttribute("cuisines", cuisineService.getCuisinesByCityName(city));
		}
		return "index";
	}
	
	@RequestMapping("/menu")
	public String menu(Model model) {
		model.addAttribute("menu_cuisines", cuisineService.getAllCuisines());
		return "menu";
	}
	
	@RequestMapping("/cuisine_detail")
    public String detail(@RequestParam(value="name", required=false, defaultValue="") String name, Model model, 
    		HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		name = new String(name.getBytes("iso8859-1"), "UTF-8");
		System.out.println(cacheManager.getCache("CuisineDetail").get(name));
		if (name.equals("")) {
			if (model.containsAttribute("cuisines")) return "index";
			else {
				model.addAttribute("cuisines", cuisineService.getCuisinesByCityName("广州"));
				return "index";
			}
		}
		ArrayList<Cuisine> cuisines = cuisineService.getCuisinesByName(name);
		if (cuisines.size() == 1) {
			Cuisine c = cuisines.get(0);
			model.addAttribute("cuisine", c);
			return "details";
		}
		else if (cuisines.size() > 1) {
			model.addAttribute("menu_cuisines", cuisines);
			return "menu";
		}
		else {
			model.addAttribute("menu_cuisines", cuisineService.getAllCuisines());
			return "menu";
		}
    }
	
	@RequestMapping("/cuisine_detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) throws UnsupportedEncodingException {
		Cuisine c = cuisineService.getCuisineById(id);
		model.addAttribute("cuisine", c);
		return "details";
    }
	
	@RequestMapping("/add/{id}/{num}")
	public void add(@PathVariable("id") int id, @PathVariable("num") int num, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("add");
		cuisineService.addToOrder(id, num, request);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
		if (cuisineService.deleteFromOrder(id, request)) {
			return "order";
		}
		else {
			return "order";
		}
	}
	
	@RequestMapping("/order")
    public String detail(Model model, HttpServletRequest request) {
		if (request.getSession().getAttribute("user") == null) {
			String msg = "请先登录";
			model.addAttribute("msg", msg);
			return "login";
		}
		return "order";
    }
	
	@RequestMapping("/orderSuccess")
    public String order(Model model) {
		return "orderSuccess";
    }
	
	@RequestMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "login";
    }
	
	@RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("user") != null) {
			request.getSession().removeAttribute("user");
		}
		return "logoutSuccess";
    }
	
	@RequestMapping("/dologin")
    public String dologin(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String newPass = MD5Util.getInstance().getMD5(password);
		
		String msg = null;
		User u = null;
		u = userService.exist(username, newPass); 
		if (u == null) {
			msg = "用户名或密码错误，请重新登录";
			model.addAttribute("msg", msg);
			return "login";
		}
		else {
			model.addAttribute("cuisines", cuisineService.getCuisinesByCityName("广州"));
			request.getSession().setAttribute("user", u);
			return "index";
		}
    }

}


