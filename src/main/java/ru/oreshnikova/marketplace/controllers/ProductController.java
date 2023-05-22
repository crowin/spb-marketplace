package ru.oreshnikova.marketplace.controllers;

import ru.oreshnikova.marketplace.dto.ProductDto;
import ru.oreshnikova.marketplace.entity.Product;
import ru.oreshnikova.marketplace.service.ProductService;
import ru.oreshnikova.marketplace.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ProductController {
    private SupplierService supplierService;
    private ProductService productsService;


    /**Добавляет продукт в базу данных
     * @return HTML-страницу, которая действует как страница добавления продукта
     */
    @PostMapping("/add")
    public String addProduct(Model material, @ModelAttribute ProductDto product) {
        productsService.addNewProduct(product);
        return "redirect:/";
    }

    /**Перейдите непосредственно на страницу редактирования корзины для редактирования свойств
     * @return HTML-страницу, которая действует как страница редактирования продукта
     */
    @GetMapping("/product/edit/{id}")
    public String goToEditPage(Model material, @PathVariable String id) {
        var foundProduct = productsService.findById(Long.valueOf(id));
        var suppliers = supplierService.getAll();
        material.addAttribute("suppliers", suppliers);
        material.addAttribute("product", foundProduct);
        return "editProduct.html";
    }

    /**Сохраняет отредактированные свойства продукта в базе данных
     * @return (перенаправляет на) HTML-страницу, которая действует как страница просмотра продукта
     */
    @GetMapping("/edit")
    public String editProduct(Model material, @ModelAttribute Product product) {
        productsService.updateById(product.getId(), product);
        return "redirect:/view";
    }

    /**Удалить продукт из базы данных
     * @return (перенаправляет на) HTML-страницу, которая действует как страница просмотра продукта
     */
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(Model material, @PathVariable String id){
        productsService.removeById(Long.valueOf(id));
        return "redirect:/view";
    }

}
