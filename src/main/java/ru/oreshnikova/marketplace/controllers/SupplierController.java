package ru.oreshnikova.marketplace.controllers;

import ru.oreshnikova.marketplace.entity.Supplier;
import ru.oreshnikova.marketplace.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class SupplierController {
    private SupplierService supplierService;

    @GetMapping("/suppliers/manage")
    public String view(Model material) {
        var suppliers = supplierService.getAll();
        material.addAttribute("supplier", new Supplier());
        material.addAttribute("suppliers", suppliers);
        return "suppliers.html";
    }

    @PostMapping("/suppliers/delete")
    public String delete(Model material, @RequestParam(value = "supplier") List<String> ids) {
        supplierService.removeById(ids.stream().map(Long::valueOf).toList());
        return "redirect:/suppliers/manage";
    }

    @PostMapping("/suppliers/add")
    public String addNew(Model material, @ModelAttribute Supplier supplier) {
        supplierService.addNewSupplier(supplier);
        return "redirect:/suppliers/manage";
    }
}
