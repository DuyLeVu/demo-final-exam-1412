package controller;

import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.CategoryService;
import service.ProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model, String Search) {
        List<Product> products = new ArrayList<>();
        if (Search == null) {
            products = productService.findAll();
        } else {
            products = productService.findByName(Search);
            model.addAttribute("backToProducts", "<a href=\"/\">Back to products\n" +
                    "        <list></list>\n" +
                    "    </a>");
        }
        model.addAttribute("products", products);
        List<Category> categories = findAllCategory(products);
        model.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "create";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(Model model, String name, int price, int quantity, String color, String description, int categoryId) {

        try {
            productService.add(new Product(1, name, price, quantity, color, description, categoryId));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("message", "New Product was created");
        return "create";
    }

    @GetMapping("/edit")
    public String showEditForm(Model model, int id) {
        Product product = productService.findById(id);

        if (product == null) {
            return ("error-404.jsp");
        } else {
            List<Category> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            return "edit";
        }
    }

    @PostMapping("/edit")
//    public String updateProduct(Model model, int id, String name, int price, int quantity, String color, String description, int categoryId) throws SQLException {
    public String updateProduct(Model model,Product product) throws SQLException{
        productService.update(product);
        model.addAttribute("message", "Product information was updated");
        return "edit";
    }

    private List<Category> findAllCategory(List<Product> products) {
        List<Category> list = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Category category = categoryService.findById(products.get(i).getCategoryId());
            list.add(category);
        }
        return list;
    }
}
