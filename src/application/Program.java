package application;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("*** TEST 1: seller findById ***");
        Seller seller = sellerDao.findById(2);
        System.out.println(seller);

        System.out.println("\n*** TEST 2: seller findByDepartment ***");
        List<Seller> list = sellerDao.findByDepartment(new Department(2, null));
        list.forEach(System.out::println);

        System.out.println("\n*** TEST 3: seller listAll ***");
        list = sellerDao.listAll();
        list.forEach(System.out::println);

        System.out.println("\n*** TEST 4: seller insert ***");
        Seller newSeller = new Seller(
                null,
                "Greg",
                "greg@gmail.com",
                new Date(),
                4000.0,
                new Department(2, null)
        );
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

        System.out.println("\n*** TEST 5: seller insert ***");
        seller = sellerDao.findById(2);
        seller.setName("Martha Wayne");
        seller.setEmail("martha@gmail.com");
        seller.getDepartment().setId(2);
        seller.setDepartment(seller.getDepartment());
        sellerDao.update(seller);
        System.out.println("Updated successfully!");

        System.out.println("\n*** TEST 6: seller delete ***");
        try {
            System.out.print("Enter Id for delete test: ");
            int id = scan.nextInt();
            sellerDao.deleteById(id);
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
