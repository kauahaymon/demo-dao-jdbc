package application;

import model.entities.Department;
import model.entities.Seller;
import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Department depart = new Department(1, "Books");
        Seller seller = new Seller(
                21,
                "Bob",
                "Bob@gmail.com",
                new Date(),
                3000.0,
                depart);
        System.out.println(seller);
    }
}
