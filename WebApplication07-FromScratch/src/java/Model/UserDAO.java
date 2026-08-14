/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class UserDAO{
    private static final List<UserDTO> fakeDB = new ArrayList<>();

    static {
        fakeDB.add(new UserDTO("admin", "1", "Admin", "ADM", true));
        fakeDB.add(new UserDTO("u2", "1", "Tran Thi Binh", "USR", true));
        fakeDB.add(new UserDTO("u3", "1", "Le Nhat Tung", "MNG", true));
        fakeDB.add(new UserDTO("u4", "1", "Hoang Van Khoe", "USR", false));
    }

    
    public boolean add(UserDTO t) {
        if (t == null || t.getUsername() == null || t.getUsername().trim().isEmpty()) {
            return false;
        }

        // Kiểm tra trùng userID
        for (UserDTO u : fakeDB) {
            if (u.getUsername().equalsIgnoreCase(t.getUsername())) {
                return false; // Da ton tai
            }
        }
        fakeDB.add(t);
        return true;
    }

    
    public boolean remove(UserDTO t) {
        if (t == null || t.getUsername() == null) {
            return false;
        }

        return fakeDB.removeIf(u -> u.getUsername().equalsIgnoreCase(t.getUsername()));
    }

    
    public boolean update(UserDTO t) {
        if (t == null || t.getUsername() == null || t.getUsername().trim().isEmpty()) {
            return false;
        }

        for (int i = 0; i < fakeDB.size(); i++) {
            if (fakeDB.get(i).getUsername().equalsIgnoreCase(t.getUsername())) {
                fakeDB.set(i, t); // Thay the thong tin moi
                return true;
            }
        }
        return false; // Khong tim thay
    }

    
    public ArrayList<UserDTO> listAll() {
         return new ArrayList<>(fakeDB);
    }

    public UserDTO searchByID(String id) {
       if (id == null || id.trim().isEmpty()) return null;
        for (UserDTO u : fakeDB) {
            if (u.getUsername().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public UserDTO checkUser(String userName, String password) {
        for(UserDTO user: fakeDB){
            if (user.getUsername().endsWith(userName) && user.getPassword().endsWith(password)) {
                return user;
            }
        }
        return null;
    }
}
