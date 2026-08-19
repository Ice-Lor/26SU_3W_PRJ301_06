package Controller;

import Model.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "search.jsp";

        try {
            String productID = request.getParameter("productID");

            if (productID != null && !productID.trim().isEmpty()) {
                ProductDAO dao = new ProductDAO();
                boolean check = dao.deleteProduct(productID);

                if (check) {
                    request.setAttribute("MESSAGE", "Delete product successfully!");
                } else {
                    request.setAttribute("ERROR_MESSAGE", "Delete product failed!");
                }
            } else {
                request.setAttribute("ERROR_MESSAGE", "Missing product ID!");
            }

            // load lại danh sách sau khi xóa
            ProductDAO dao = new ProductDAO();
            request.setAttribute("LIST", dao.getListProduct(""));
            url = "search.jsp";

        } catch (Exception e) {
            log("Error at DeleteController: " + e.toString());
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