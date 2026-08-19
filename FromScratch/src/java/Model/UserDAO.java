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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserDAO {
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
    
    public UserDTO searchByID(String id) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        
        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT * FROM [user] WHERE userID = ?";
            ptm = conn.prepareStatement(sql);
            ptm.setString(1, id);
            rs = ptm.executeQuery();
            
            if (rs.next()) {
                UserDTO user = new UserDTO();
                user.setFullName(rs.getNString("fullName"));
                user.setUserID(rs.getString("userID"));
                user.setPassword(rs.getString("password"));
                user.setRoleID(rs.getString("roleID"));
                user.setStatus(rs.getBoolean("status"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, rs);
        }
        return null;
    }
    
    public List<UserDTO> getListUser(String txtKeywords)throws SQLException, ClassNotFoundException{
        List<UserDTO> listUser = new ArrayList<UserDTO>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try{
            conn = DbUtils.getConnection();
            if (conn!= null) {
                String sql = "SELECT * FROM [user] WHERE name LIKE ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + txtKeywords + "%");
                rs = ptm.executeQuery();
                while(rs.next()){
                    String uID = rs.getString("uID");
                    String name = rs.getString("name");
                    String roleID = rs.getString("roleID");
                    String password = "******";
                    boolean status = rs.getBoolean("status");
                    listUser.add(new UserDTO(uID,name,roleID,password,status));
                }
            }
        } finally {
            closeResources(conn,ptm,rs);
        }
        return listUser;
    }
    
    public boolean insertUser(UserDTO user) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO [user](userID, fullName, password, roleID, status) VALUES (?, ?, ?, ?, ?)";
                ptm = conn.prepareStatement(sql);

                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getRoleID());
                ptm.setBoolean(5, user.isStatus());

                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error at UserDAO.insertUser: " + e.toString());
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }

    public boolean updateUser(UserDTO user) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [user] "
                           + "SET fullName = ?, password = ?, roleID = ?, status = ? "
                           + "WHERE userID = ?";
                ptm = conn.prepareStatement(sql);

                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getPassword());
                ptm.setString(3, user.getRoleID());
                ptm.setBoolean(4, user.isStatus());
                ptm.setString(5, user.getUserID());

                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error at UserDAO.updateUser: " + e.toString());
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }

    public boolean updateStatusUser(String userID, boolean status) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE [user] SET status = ? WHERE userID = ?";
                ptm = conn.prepareStatement(sql);

                ptm.setBoolean(1, status);
                ptm.setString(2, userID);

                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error at UserDAO.updateStatusUser: " + e.toString());
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }

    public boolean deleteUser(String userID) {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM [user] WHERE userID = ?";
                ptm = conn.prepareStatement(sql);

                ptm.setString(1, userID);

                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error at UserDAO.deleteUser: " + e.toString());
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }
}
