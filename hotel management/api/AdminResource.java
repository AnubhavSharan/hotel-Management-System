package api;
import java.util.Collection;
import model1.Customer;
import model1.FreeRoom;
import model1.IRoom;
import model1.RoomType;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {
private static AdminResource instance;

    
    private AdminResource(){}

    public static AdminResource getInstance() {
        if (instance == null) {
            instance = new AdminResource();
        }
        return instance;
    }

    ReservationService service=ReservationService.getInstance();
    CustomerService c=CustomerService.getInstance();
    public Customer getCustomer(String email){
        return c.getACustomer(email);
    }

    public void addARoom(IRoom room){
        service.addRoom(room);
    }

    public void addFreeRoom(FreeRoom room){
        service.freeRoom(room);
    }

    public Collection<IRoom> getAllRooms(){
        return service.getAllroom();
    }

    public Collection<Customer> getAllCustomers(){
        return c.getAllCustomer();
    }

    public void displayAllReservations(){
        service.printAllReservation();
    }

}
