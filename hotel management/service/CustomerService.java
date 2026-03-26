package service;
import model1.Customer;
import java.util.*;

public class CustomerService {
private static CustomerService instance; 

    private Collection<Customer> customers=new ArrayList<>();

     public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void addCustomer(String email,String firstName,String lastName){

        for(Customer c : customers){
        if(c.getEmail().equals(email)){
            System.out.println("Customer already exists........");
            return;
        }
    }
        Customer customer=new Customer(firstName, lastName, email);
        customers.add(customer);
    }

    public Customer getACustomer(String customerEMail){
        for(Customer cust:customers){
            if(cust.getEmail().equals(customerEMail)){
                return cust;
            }
        }
        return null;

    }

    public Collection<Customer> getAllCustomer(){
        return customers;
    }

    

}
