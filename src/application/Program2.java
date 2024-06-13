package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;

public class Program2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n" + "*** TEST 1: department findById ***");
        Department department = departmentDao.findById(4);
        System.out.println(department);

        System.out.println("\n" + "*** TEST 2: department listAll ***");
        List<Department> list = departmentDao.listAll();
        list.forEach(System.out::println);

        System.out.println("\n" + "*** TEST 3: department insert ***");
        Department newDepartment = new Department(6, "Computing");
        departmentDao.insert(newDepartment);
        System.out.println("New department inserted! Id = " + newDepartment.getId());
    }
}
