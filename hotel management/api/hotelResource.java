package api;
import java.util.Collection;
import java.time.LocalDate;
import model1.Customer;
import model1.IRoom;
import model1.Reservation;
import service.CustomerService;
import service.ReservationService;
import model1.RoomType;
public class hotelResource {

    private static hotelResource instance;

    
    private hotelResource(){}

    public static hotelResource getInstance() {
        if (instance == null) {
            instance = new hotelResource();
        }
        return instance;
    }

    CustomerService c=CustomerService.getInstance();
    ReservationService service=ReservationService.getInstance();
    public Customer getCustomer(String email){
        Customer cust=c.getACustomer(email);
        if(cust==null){
            return null;
        }

        return cust;
    }

    public void createACustomer(String email,String firstName,String lastName){
        c.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber){
        IRoom room=service.getARoom(roomNumber);
        if(room==null){
            return null;
        }
        return room;
    }

    public Reservation bookARoom(String customerEmail,IRoom room,LocalDate checkInDate,LocalDate checkOutDate){
        Customer cust=c.getACustomer(customerEmail);
        Reservation newRes=service.reserveARoom(cust, room, checkInDate, checkOutDate);
        if(newRes==null){
            return null;
        }
        return newRes;
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer cust=c.getACustomer(customerEmail);
        if(service.getCustomerReservation(cust)==null){
            return null;
        }
        return service.getCustomerReservation(cust);
    }

   

    public Collection<IRoom> findARoom(LocalDate checkIn,LocalDate checkOut){
        return service.findRoom(checkIn, checkOut);

    }
}
