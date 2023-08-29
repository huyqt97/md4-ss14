package ra.productcrud.controller;

import ra.productcrud.model.Product;
import ra.productcrud.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    protected ProductService productService;

    @Override
    public void init() throws ServletException {
        productService= new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null){
            displayProducts(productService.findAll(),request,response);
        }else {
            switch (action){
                case "CREATE":
                    request.getRequestDispatcher("/WEB-INF/newProduct.jsp").forward(request,response);
                    break;
                case "EDIT":
                    Long idEdit = Long.parseLong(request.getParameter("id"));
                    Product product = productService.findById(idEdit);
                    request.setAttribute("product",product);
                    request.getRequestDispatcher("/WEB-INF/editProduct.jsp").forward(request,response);
                    break;
                    case "DELETE":
                        Long idDel = Long.parseLong(request.getParameter("id"));
                        productService.delete(idDel);
                        displayProducts(productService.findAll(),request,response);
                case "SEARCH":

                    String name = request.getParameter("searchname");
request.setAttribute("searchname",name);
                        displayProducts(productService.sreachname(name),request,response);
                        break;
            }
        }
    }

    protected void displayProducts(List<Product>list,HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("products",list);
        request.getRequestDispatcher("/WEB-INF/listProducts.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if(action!=null){
            switch (action){
                case "ADD":
                    String name = request.getParameter("name");
                    String des = request.getParameter("des");
                    Double price = Double.valueOf(request.getParameter("price"));
                    int stock = Integer.parseInt(request.getParameter("stock"));
                    String imageUrl = request.getParameter("imageUrl");
                    Product newProduct= new Product(null,name,des,price,stock,imageUrl);
                    productService.save(newProduct);
                    response.sendRedirect(request.getContextPath()+ "/ProductController");
                    break;
                case "UPDATE":
                    long id = Long.parseLong(request.getParameter("id"));
                    String nameUp = request.getParameter("name");
                    String desUp = request.getParameter("des");
                    Double priceUp = Double.valueOf(request.getParameter("price"));
                    int stockUp = Integer.parseInt(request.getParameter("stock"));
                    String imageUrlUp = request.getParameter("imageUrl");
                    Product UpProduct= new Product(id,nameUp,desUp,priceUp,stockUp,imageUrlUp);
                    productService.save(UpProduct);
                    response.sendRedirect(request.getContextPath()+ "/ProductController");
                    break;

            }
        }
    }
}