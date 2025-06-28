package main.java.Restaurent_Ordering_System.service;

import main.java.Restaurent_Ordering_System.dao.MenuDAO;
import main.java.Restaurent_Ordering_System.dao.OrderDAO;
import main.java.Restaurent_Ordering_System.dao.UserDAO;
import main.java.Restaurent_Ordering_System.util.DBUtil;

public class ServiceFactory {

    private static MenuDAO menuDAO;
    private static OrderDAO orderDAO;
    private static UserDAO userDAO;

    public static MenuDAO getMenuDAO() {
        if (menuDAO == null) {
            try {
                menuDAO = new MenuDAO(DBUtil.getInstance().getConnection());
            } catch (Exception e) {
                throw new RuntimeException("Failed to create MenuDAO", e);
            }
        }
        return menuDAO;
    }

    public static OrderDAO getOrderDAO() {
        if (orderDAO == null) {
            try {
                orderDAO = new OrderDAO(DBUtil.getInstance().getConnection());
            } catch (Exception e) {
                throw new RuntimeException("Failed to create OrderDAO", e);
            }
        }
        return orderDAO;
    }

    public static UserDAO getUserDAO() {
        if (userDAO == null) {
            try {
                userDAO = new UserDAO(DBUtil.getInstance().getConnection());
            } catch (Exception e) {
                throw new RuntimeException("Failed to create UserDAO", e);
            }
        }
        return userDAO;
    }
}
