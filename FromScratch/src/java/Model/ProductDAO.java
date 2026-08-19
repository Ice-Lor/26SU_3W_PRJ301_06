/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProductDAO {
    private void closeResources(Connection conn, PreparedStatement ptm, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProductDTO> getListProduct(String txtKeywords){
        List<ProductDTO> listProduct = new ArrayList<ProductDTO>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DbUtils.getConnection();
            if (conn!= null) {
                String sql = "SELECT * FROM [Product] WHERE productName LIKE ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + txtKeywords + "%");
                rs = ptm.executeQuery();
                while(rs.next()){
                    String pID = rs.getString("productID");
                    String pName = rs.getString("productName");
                    String pDes = rs.getString("description");
                    double pPrice = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    LocalDateTime time = rs.getTimestamp("createdAt").toLocalDateTime();
                    listProduct.add(new ProductDTO(pID,pName,pDes,pPrice,quantity,image,status,time));
                }
            }
        }
        catch(ClassNotFoundException | SQLException E){
            E.printStackTrace();
        }
        finally{
            closeResources(conn,ptm,rs);
        }
        return listProduct;
    }

    public ProductDTO searchByID(String productID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM [Product] WHERE productID = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    String pID = rs.getString("productID");
                    String pName = rs.getString("productName");
                    String pDes = rs.getString("description");
                    double pPrice = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    LocalDateTime time = rs.getTimestamp("createdAt").toLocalDateTime();
                    return new ProductDTO(pID, pName, pDes, pPrice, quantity, image, status, time);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, rs);
        }

        return null;
    }
    
    public boolean insertProduct(ProductDTO product) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [Product](productID, productName, description, price, quantity, image, status, createdAt) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                ptm = conn.prepareStatement(sql);

                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getProductName());
                ptm.setString(3, product.getDescription());
                ptm.setDouble(4, product.getPrice());
                ptm.setInt(5, product.getQuantity());
                ptm.setString(6, product.getImage());
                ptm.setBoolean(7, product.isStatus());
                ptm.setTimestamp(8, Timestamp.valueOf(product.getCreatedAt()));

                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error at ProductDAO.insertProduct: " + e.toString());
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }

    public boolean updateProduct(ProductDTO product) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [Product] "
                           + "SET productName = ?, description = ?, price = ?, quantity = ?, image = ?, status = ? "
                           + "WHERE productID = ?";
                ptm = conn.prepareStatement(sql);

                ptm.setString(1, product.getProductName());
                ptm.setString(2, product.getDescription());
                ptm.setDouble(3, product.getPrice());
                ptm.setInt(4, product.getQuantity());
                ptm.setString(5, product.getImage());
                ptm.setBoolean(6, product.isStatus());
                ptm.setString(7, product.getProductID());

                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error at ProductDAO.updateProduct: " + e.toString());
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }

    public boolean deleteProduct(String productID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM [Product] WHERE productID = ?";
                ptm = conn.prepareStatement(sql);

                ptm.setString(1, productID);

                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error at ProductDAO.deleteProduct: " + e.toString());
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }
    
    public boolean updateStatusProduct(String productID, boolean status) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [Product] SET status = ? WHERE productID = ?";
                ptm = conn.prepareStatement(sql);

                ptm.setBoolean(1, status);
                ptm.setString(2, productID);

                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error at ProductDAO.deleteProduct: " + e.toString());
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }
}
