package Controller;

import Model.ProductDAO;
import Model.ProductDTO;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = "search.jsp";

        try {
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String image = request.getParameter("image");
            String statusStr = request.getParameter("status");

            if (productID != null && !productID.trim().isEmpty()) {
                ProductDAO dao = new ProductDAO();

                ProductDTO oldProduct = dao.searchByID(productID);
                if (oldProduct != null) {
                    ProductDTO product = new ProductDTO();
                    product.setProductID(productID);
                    product.setProductName(productName);
                    product.setDescription(description);
                    product.setPrice(Double.parseDouble(priceStr));
                    product.setQuantity(Integer.parseInt(quantityStr));
                    product.setImage(image);

                    if (statusStr != null && (statusStr.equals("1") || statusStr.equalsIgnoreCase("true") || statusStr.equalsIgnoreCase("on"))) {
                        product.setStatus(true);
                    } else {
                        product.setStatus(false);
                    }

                    product.setCreatedAt(oldProduct.getCreatedAt());

                    boolean check = dao.updateProduct(product);

                    if (check) {
                        request.setAttribute("MESSAGE", "Update product successfully!");
                    } else {
                        request.setAttribute("ERROR_MESSAGE", "Update product failed!");
                    }
                } else {
                    request.setAttribute("ERROR_MESSAGE", "Product not found!");
                }
            } else {
                request.setAttribute("ERROR_MESSAGE", "Missing product ID!");
            }

            // load lại danh sách để view còn giữ data sau update
            ProductDAO dao = new ProductDAO();
            request.setAttribute("LIST", dao.getListProduct(""));
            url = "search.jsp";

        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
            request.setAttribute("ERROR_MESSAGE", "Error: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}