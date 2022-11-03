/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitymanagerdemo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Address;
import model.Customer;

/**
 *
 * @author sarun
 */
public class EntityManagerDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        createData();
//        printAllCustomer();
        printCustomerByCity("Bangkok");


    }

    public static void printAllCustomer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        List<Address> addrList = (List<Address>) em.createNamedQuery("Address.findAll").getResultList();
        List<Customer> custList = (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
        System.out.println("-------------------------------------");
        for (Customer c : custList) {
            for (Address a : addrList) {
                if ((c.getId() == a.getCustomerFk().getId())) {
                    System.out.println("First Name: " + c.getFirstname() + " ");
                    System.out.println("Last Name: " + c.getLastname() + " ");
                    System.out.println("Email: " + c.getEmail() + " ");
                    System.out.println("Street: " + a.getStreet() + " ");
                    System.out.println("City: " + a.getCity() + " ");
                    System.out.println("Country: " + a.getCountry() + " ");
                    System.out.println("Zip Code: " + a.getZipcode() + " ");
                    System.out.println("-------------------------------------");
                    System.out.println("-------------------------------------");
                }

            }
        }

    }

    public static void printCustomerByCity(String city) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
       List<Address> addrList = (List<Address>) em.createNamedQuery("Address.findByCity").setParameter("city", city).getResultList();
        List<Customer> custList = (List<Customer>) em.createNamedQuery("Customer.findAll").getResultList();
        System.out.println("-------------------------------------");
        for (Customer c : custList) {
            for (Address a : addrList) {
                if ((c.getId() == a.getCustomerFk().getId()) && city.equals(a.getCity())) {
                    System.out.println("First Name: " + c.getFirstname() + " ");
                    System.out.println("Last Name: " + c.getLastname() + " ");
                    System.out.println("Email: " + c.getEmail() + " ");
                    System.out.println("Street: " + a.getStreet() + " ");
                    System.out.println("City: " + a.getCity() + " ");
                    System.out.println("Country: " + a.getCountry() + " ");
                    System.out.println("Zip Code: " + a.getZipcode() + " ");
                    System.out.println("-------------------------------------");
                    System.out.println("-------------------------------------");
                }

            }
        }
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemoPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static void createData() {
        Customer customer1 = new Customer(1L, "John", "Henry", "jh@mail.com");
        Customer customer2 = new Customer(2L, "Marry", "Jane", "mj@mail.com");
        Customer customer3 = new Customer(3L, "Peter", "Parker", "pp@mail.com");
        Customer customer4 = new Customer(4L, "Bruce", "Wayn", "bw@mail.com");
        Address address1 = new Address(1L, "123/4 Viphavadee Rd.", "Bangkok", "TH", "10900");
        Address address2 = new Address(2L, "123/5 Viphavadee Rd.", "Bangkok", "TH", "10900");
        Address address3 = new Address(3L, "543/21 Fake Rd.", "Nonthaburi", "TH", "20900");
        Address address4 = new Address(4L, "678/90 Unreal Rd.", "Pathumthani", "TH", "30500");

        persist(customer1);
        address1.setCustomerFk(customer1);
        persist(address1);

        persist(customer2);
        address2.setCustomerFk(customer2);
        persist(address2);

        persist(customer3);
        address3.setCustomerFk(customer3);
        persist(address3);

        persist(customer4);
        address4.setCustomerFk(customer4);
        persist(address4);
    }

}
