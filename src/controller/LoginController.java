package controller;

public class LoginController {

    public boolean login(
            String username,
            String password) {

        return username.equals("admin")
                && password.equals("123");
    }
}