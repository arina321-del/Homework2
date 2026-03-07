package com.homework2.userservice;

import com.homework2.userservice.dao.UserDao;
import com.homework2.userservice.dao.UserDaoImpl;
import com.homework2.userservice.service.UserService;
import com.homework2.userservice.ui.ConsoleMenu;
import com.homework2.userservice.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        UserDao userDao = new UserDaoImpl(sessionFactory);

        UserService userService = new UserService(userDao);

        ConsoleMenu consoleMenu = new ConsoleMenu(userService);

        consoleMenu.start();
    }
}