package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.CuisinesDaoImpl;
import entity.Cuisine;
import service.CuisineServiceImpl;

@Controller
public class CuisineController {
	
	private CuisineServiceImpl cs;
	
	public CuisineController() {
		cs = new CuisineServiceImpl();
	}
	
	@RequestMapping(value={"/", "/index"})
    public String index(@RequestParam(value="city", required=false, defaultValue="广州") String city, Model model, HttpServletRequest request)
    		throws IOException {
		if(request.getSession().getAttribute("order") != null)  {
			request.getSession().removeAttribute("order");
		}
		city = new String(city.getBytes("iso8859-1"), "UTF-8");
		if (cs.getCuisinesByCityName(city).size() == 0) {
			model.addAttribute("cuisines", cs.getCuisinesByCityName("广州"));
		}
		else {
			model.addAttribute("cuisines", cs.getCuisinesByCityName(city));
		}
		return "index";
	}
	
	@RequestMapping("/menu")
	public String menu(Model model) {
		model.addAttribute("menu_cuisines", cs.getAllCuisines());
		return "menu";
	}
	
	@RequestMapping("/cuisine_detail")
    public String detail(@RequestParam(value="name", required=false, defaultValue="") String name, Model model, 
    		HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		name = new String(name.getBytes("iso8859-1"), "UTF-8");
		if (name.equals("")) {
			if (model.containsAttribute("cuisines")) return "index";
			else {
				model.addAttribute("cuisines", cs.getCuisinesByCityName("广州"));
				return "index";
			}
		}
		ArrayList<Cuisine> cuisines = cs.getCuisinesByName(name);
		if (cuisines.size() == 1) {
			Cuisine c = cuisines.get(0);
			model.addAttribute("cuisine", c);
			return "details";
		}
		else if (cuisines.size() > 1) {
			model.addAttribute("cuisines", cuisines);
			return "menu";
		}
		else {
			model.addAttribute("cuisines", cs.getAllCuisines());
			return "menu";
		}
    }
	
	@RequestMapping("/cuisine_detail/{id}")
    public String detail(@PathVariable("id") int id, Model model) throws UnsupportedEncodingException {
		Cuisine c = cs.getCuisineById(id);
		model.addAttribute("cuisine", c);
		return "details";
    }
	
	@RequestMapping("/add/{id}/{num}")
	public void add(@PathVariable("id") int id, @PathVariable("num") int num, HttpServletRequest request, HttpServletResponse response) {
		cs.addToOrder(id, num, request);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response) {
		if (cs.deleteFromOrder(id, request)) {
			return "order";
		}
		else {
			return "order";
		}
	}
	
	@RequestMapping("/show")
    public String detail(Model model) {
		return "order";
    }
	
	@RequestMapping("/orderSuccess")
    public String order(Model model) {
		return "orderSuccess";
    }
	
}
