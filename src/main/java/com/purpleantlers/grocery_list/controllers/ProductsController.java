package com.purpleantlers.grocery_list.controllers;

import java.util.List;

import com.purpleantlers.grocery_list.models.ProductDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.purpleantlers.grocery_list.models.Product;
import com.purpleantlers.grocery_list.repository.ProductsRepository;


@Controller
@RequestMapping("")

public class ProductsController {
    @Autowired
    private ProductsRepository repo;

//  RECUPERAR TODOS LOS PRODUCTOS DE LA BASE DE DATOS
    @GetMapping({"", "/"})
    public String showProductList (Model model) {
        List<Product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }

//  AÑADIR LA INFORMACIÓN DEL INPUT AL OBJETO productDto
    @GetMapping("/add")
    public String showAddPage (Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/addProduct";
    }

//  GUARDAR LA INFORMACIÓN AÑADIDA EN EL OBJETO productDto EN LA BASE DE DATOS
    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {

        try {
            Product product = new Product();
            product.setName(productDto.getName());
            repo.save(product);
        }
        catch (Exception exception) {
            System.out.println("Exception:" + exception.getMessage());
        }

        return "redirect:/";
    }

//  ELIMINAR LA INFORMACIÓN DE LA BASE DE DATOS A PARTIR DE LA ID DEL PRODUCTO
    @GetMapping("/delete")
    public String deleteProduct (@RequestParam int id) {

        try {
            Product product = repo.findById(id).get();
            repo.delete(product);
        }
        catch (Exception exception) {
            System.out.println("Exception:" + exception.getMessage());
        }

        return "redirect:/";
    }
}
