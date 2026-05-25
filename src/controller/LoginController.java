package controller;

public class LoginController {

    public boolean login(String username, String password) {
        
        // Cek apakah username dan password sesuai
        if (username == null || password == null) {
            return false;
        }
        
        
        return username.equals("admin") && password.equals("123");
    }
}