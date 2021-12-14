package controller;

import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
//                String txtSearch = request.getParameter("Search");

        List<Product> products = new ArrayList<>();
//        products = productDAO.findAll();


        if (Search == null) {
            products = productService.findAll();
        } else {
            products = productService.findByName(Search);
        }
        model.addAttribute("products", products);
//        request.setAttribute("products", products);
        List<Category> categories = findAllCategory(products);
        model.addAttribute("categories", categories);
        model.addAttribute("backToProducts", "<a href=\"/\">Back to products\n" +
                "        <list></list>\n" +
                "    </a>");
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
    public String showEditForm(Model model, int id){
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

    @PostMapping("/updateProduct")
    public String updateProduct(Model model, String name, int price, int quantity, String color, String description, int categoryId) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Product product = new Product(id, name, price, quantity, color, description, categoryId);
        try {
            productDAO.update(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("product", product);
        request.setAttribute("message", "Product information was updated");
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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