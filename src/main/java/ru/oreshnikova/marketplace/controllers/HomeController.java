package ru.oreshnikova.marketplace.controllers;

import ru.oreshnikova.marketplace.dto.ProductDto;
import ru.oreshnikova.marketplace.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller @AllArgsConstructor
public class HomeController {

	private SupplierService supplierService;

	@GetMapping("/login")
	public String login() {
		return "login.html";
	}

	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

	/**Указывает на страницу добавления, через которую пользователь может добавить продукт
	 * @return HTML-страницу, которая действует как страница добавления продукта
	 */
	@GetMapping("/list")
	public String goToAddProductPage(Model material) {
		material.addAttribute("suppliers", supplierService.getAll());
		material.addAttribute("product", new ProductDto());
		return "addProduct.html";
	}

	/**Просматривает/Возвращает все продукты, присутствующие в базе данных
	 * @return HTML-страницу, которая действует как страница просмотра продукта
	 */
	@GetMapping("/view")
	public String viewProduct(Model material) {
		var suppliers = supplierService.getAll();
		material.addAttribute("suppliers", suppliers);
		return "viewProduct.html";
	}

	/**Перейти непосредственно на домашнюю страницу приложения
	 * @return HTML-страницу, которая действует как домашняя страница
	 */
	@GetMapping("/")
	public String goHome(Model material) {
		return "home.html";
	}

	/**Перенаправляет на страницу поиска для поиска продукта
	 * @return HTML-страницу, которая действует как страница поиска продукта
	 */
	@GetMapping("/search")
	public String goToSearchPage() {
		return "searchProduct.html";
	}
}
