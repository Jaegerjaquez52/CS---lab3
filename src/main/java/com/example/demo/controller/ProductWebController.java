package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable; // <--- ОСЬ ЦЬОГО НЕ ВИСТАЧАЛО
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductWebController {

    private final ProductService productService;

    public ProductWebController(ProductService productService) {
        this.productService = productService;
    }

    // Сторінка зі списком товарів
    @GetMapping("/products")
    public String viewHomePage(Model model) {
        model.addAttribute("listProducts", productService.findAll());
        return "products";
    }

    // Сторінка додавання нового товару
    @GetMapping("/products/new")
    public String showNewProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create_product";
    }

    // Обробка кнопки "Зберегти" (працює і для створення, і для оновлення)
    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    // Відкрити форму редагування
    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        model.addAttribute("product", product);
        return "edit_product";
    }

    // Видалити товар
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}