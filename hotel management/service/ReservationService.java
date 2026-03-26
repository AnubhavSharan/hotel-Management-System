package service;
import java.util.*;
import java.time.LocalDate;
import model1.Customer;
import model1.IRoom;
import model1.Reservation;
import model1.RoomType;
import model1.FreeRoom;

public class ReservationService {
    private static ReservationService instance; 

    private Collection<IRoom> irooms=new ArrayList<>();
    private Collection<Reservation> reservations=new ArrayList<>();
    private ReservationService() {}

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }
    
    public void addRoom(IRoom room){
        for(IRoom r:irooms){
            if(r.getRoomNumber().equals(room.getRoomNumber())){
                System.out.println("room already exist......");
                return;
            }
        }
        irooms.add(room);
    }

    public void freeRoom(FreeRoom room){
        for(IRoom r:irooms){
            if(r.getRoomNumber().equals(room.getRoomNumber())){
                System.out.println("room already exist......");
                return;
            }
        }
        
        irooms.add(room);

    }

    public Collection<IRoom> getAllroom(){
        if(irooms.isEmpty()){
            return null;
        }
        return irooms;
        
    }
    public IRoom getARoom(String roomId){
        for(IRoom ir:irooms){
            if(ir.getRoomNumber().equals(roomId)){
                return ir;
            }
        }
        return null;
    }

    public Reservation reserveARoom(Customer customer,IRoom room,LocalDate checkIDate,LocalDate checkODate){
        for(Reservation check:reservations){
            if((check.getRoom().getRoomNumber().equals(room.getRoomNumber()) && checkIDate.isBefore(check.getCheckOutDate()) && checkODate.isAfter(check.getCheckInDate())) || (check.getRoom().getRoomNumber().equals(room.getRoomNumber()) && checkIDate.equals(check.getCheckOutDate()) && checkODate.equals(check.getCheckInDate())) ){
                return null;
            }
        }

        Reservation reservation=new Reservation(customer, room, checkIDate, checkODate);
        
            reservations.add(reservation);
            return reservation;
    }



    public Collection<IRoom> findRoom(LocalDate checkIDate,LocalDate checkODate){
        Collection<IRoom> isAvailable=new ArrayList<>();
        for(IRoom room:irooms){
            boolean available=true;
            for(Reservation re:reservations){
                if(room.getRoomNumber().equals(re.getRoom().getRoomNumber()) && checkIDate.isBefore(re.getCheckOutDate()) && checkODate.isAfter(re.getCheckInDate())){
                   available=false;
                   break;
                }
            }
            if(available){
                isAvailable.add(room);
            }
        }
        return isAvailable;
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        Collection<Reservation> cutomerReservation=new ArrayList<>();
        for(Reservation re:reservations){
            if(re.getcustomer().equals(customer)){
                cutomerReservation.add(re);
            }
        }
        return cutomerReservation;
    }

    public void printAllReservation(){
        if(reservations.isEmpty()){
            System.out.println("No reservations available");
            return;
        }
        System.out.print("Reservations : ");
        System.out.println(reservations.toString());
    }
}



