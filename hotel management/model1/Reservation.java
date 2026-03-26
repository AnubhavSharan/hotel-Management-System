package model1;
import java.time.LocalDate;
public class Reservation {
    private Customer customer;
    private IRoom room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    
    public Reservation(Customer customer,IRoom room,LocalDate checkInDate,LocalDate checkOutDate){
        this.customer=customer;
        this.room=room;
        this.checkInDate=checkInDate;
        this.checkOutDate=checkOutDate;

    }

    public Customer getcustomer(){
        return this.customer;
    }

    public IRoom getRoom(){
        return room;
    }

    public LocalDate getCheckInDate(){
        return checkInDate;
    }

    public LocalDate getCheckOutDate(){
        return checkOutDate;
    }
    

    @Override
    public String toString(){
        return "Reservation Details{"+
                "\n     Customer : "+customer+
                "\n     Room : "+room+
                "\n     Check In Date : "+checkInDate+
                "\n     Check out Date : "+checkOutDate+
                "\n  }";
    }
}

