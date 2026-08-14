/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class UserDTO {
    private String username;
    private String password;
    private String fullName;
    private String roleID;
    private boolean status;

    public UserDTO() {
        this.username = "";
        this.password = "";
        this.roleID = "";
        this.fullName ="";
        this.status = false;
    }

    public UserDTO(String username, String password, String fullName, String roleID, boolean status) {
        this.username = username;
        this.password = password;
        this.roleID = roleID;
        this.fullName = fullName;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
