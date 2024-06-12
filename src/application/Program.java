package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {

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
    }
}
