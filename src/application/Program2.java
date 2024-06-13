package application;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n" + "*** TEST 1: department findById ***");
        Department department = departmentDao.findById(4);
        System.out.println(department);

        System.out.println("\n" + "*** TEST 2: department listAll ***");
        List<Department> list = departmentDao.listAll();
        list.forEach(System.out::println);

        System.out.println("\n" + "*** TEST 3: department insert ***");
        Department newDepartment = new Department(null, "Computing");
        departmentDao.insert(newDepartment);
        System.out.println("New department inserted! Id = " + newDepartment.getId());

        System.out.println("\n" + "*** TEST 4: department update ***");
        department = departmentDao.findById(6);
        department.setName("Cooking");
        departmentDao.update(department);
        System.out.println("Updated successfully!");

        System.out.println("\n" + "*** TEST 4: department update ***");
        try {
            System.out.print("Enter Id for delete test: ");
            int id = scan.nextInt();
            departmentDao.deleteById(id);
            System.out.println("Deleted successfully!");
        }
        catch (InputMismatchException e) {
            System.out.println("Incompatible input type!");
        }
        catch (DbException e) {
            System.out.println(e.getMessage());
        }
        finally {
            scan.close();
        }
    }
}
