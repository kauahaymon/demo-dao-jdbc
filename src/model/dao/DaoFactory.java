package model.dao;

import db.DB;
import model.impl.DepartmentDaoJDBC;
import model.impl.SellerDaoJDBC;

public class DaoFactory {
    // Here you'll find static methods to create DAO instances

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
