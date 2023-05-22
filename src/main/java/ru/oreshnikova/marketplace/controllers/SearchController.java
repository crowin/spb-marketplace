package ru.oreshnikova.marketplace.controllers;

import ru.oreshnikova.marketplace.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@AllArgsConstructor
public class SearchController {
    private ProductService productService;

    /**Выполняет поиск продукта по идентификатору и возвращает результат
     * @return HTML-страницу, которая действует как страница просмотра продукта
     */
    @GetMapping("/searchBy")
    public String searchProductId(Model material, @RequestParam Map<String, String> params) {
        if (params.isEmpty()) return "redirect:/search";
        var foundProducts = productService.searchByParam(params);

        material.addAttribute("suppliers", foundProducts);
        return "viewProduct.html";
    }
}
