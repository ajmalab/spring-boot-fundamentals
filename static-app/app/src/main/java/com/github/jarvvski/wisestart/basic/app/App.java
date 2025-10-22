package com.github.jarvvski.wisestart.basic.app;

import com.github.jarvvski.wisestart.basic.app.logic.AdminController;
import com.github.jarvvski.wisestart.basic.app.logic.DatabaseConnection;
import com.github.jarvvski.wisestart.basic.app.logic.DatabaseManager;
import com.github.jarvvski.wisestart.basic.app.logic.UserController;
import com.github.jarvvski.wisestart.basic.app.logic.UserRegistrationService;
import com.github.jarvvski.wisestart.basic.app.logic.UserRepository;

public class App {
    public static void main(String[] args) {
        new App().start();
    }

    void start() {
        // todo - create an instance of AdminController and UserContoller
        UserController userController = new UserController();
        AdminController adminController = new AdminController();
    }
}
