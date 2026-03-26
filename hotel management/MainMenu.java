import java.util.Collection;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.text.ParseException;
import api.hotelResource;
import model1.*;
import api.AdminResource;

public class MainMenu {
    hotelResource h=hotelResource.getInstance();
    AdminResource a=AdminResource.getInstance();

    public void menu(){
        while(true){
            System.out.println("Welcome to the hotel reservation application");
            System.out.println("---------------------------------------------------------------");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            Scanner sc=new Scanner(System.in);
        String choice=sc.next();
        switch (choice) {
                case "1":
                    find();
                    break;
                case "2":
                    seeCustomerReservation();
                    break;
                case "3":
                    createAccount();
                    break;
            
                case "4":
                    admin();
                    break;

                case "5":
                    System.out.println("Program terminating...");
                    System.exit(0);
                
                default:
                    System.out.println("please enter valid input!!!!");
                    break;
            }
        }
    }

     public void find(){
        LocalDate checkin=null;
        LocalDate checkOut=null;
        LocalDate today=LocalDate.now();
        Scanner sc=new Scanner(System.in);
        System.out.println("Please enter the check-in date : ");
        String check_in=sc.next();
        checkin=date_validator(check_in);
        if(checkin==null || checkin.isBefore(today)){
            find();
        }else{
            System.out.println("Please enter the check-Out date : ");
            String check_out=sc.next();
            checkOut=date_validator(check_out);
            if(checkOut==null || checkOut.isBefore(today) ){
                find();
            }else{
                if(checkin.isAfter(checkOut)){
                    System.out.println("please enter valid date");
                    find();
                }
                
                
                Collection<IRoom> rooms=h.findARoom(checkin, checkOut);
                if(rooms.isEmpty()){
                    availableForWeek(checkin,checkOut);
                    return;
                }
                System.out.println("these are the available rooms");
                System.out.println(h.findARoom(checkin, checkOut));  
                System.out.println("Do you want to reserve a room");                     
                    
                String input=sc.next();
                if(input.equals("y") || input.equals("yes")){
                    reserve(checkin, checkOut);
                        
                }else if(input.equals("n") || input.equals("no")){
                    menu();                       
                }else{
                    System.out.println("Please choose y/yes or n/no");
                    find();
                }

            }
        }   

    }

    public void availableForWeek(LocalDate checkin,LocalDate checkOut){        
        // LocalDate altCheckIn = checkin.plusDays(7);
        LocalDate altCheckOut = checkOut.plusDays(7);
        System.out.println("No rooms for selected dates");
       
        LocalDate ch=null;
        LocalDate check_in = checkin.plusDays(1);
        long gapDays = ChronoUnit.DAYS.between(checkin, checkOut);
        Collection<IRoom> available_rooms = h.findARoom(checkin, checkOut);
        while (available_rooms.isEmpty() && !check_in.isAfter(altCheckOut)) {
            LocalDate check_out = check_in.plusDays(gapDays);
            available_rooms = h.findARoom(check_in, check_out); 
            if (available_rooms.isEmpty()) {
                check_in = check_in.plusDays(1); 
            }else{
                System.out.println("New available days found");
                System.out.println(check_in);
                System.out.println(check_out);
                ch=check_out;

            }
        }

        System.out.println("Would you like to see available rooms: (y/n)");
        Scanner sc=new Scanner(System.in);
        String input=sc.next();
        if(input.equals("y") || input.equals("yes")){
            available_rooms = h.findARoom(check_in, ch);
            System.out.println(available_rooms);
            System.out.println("Would you like to book a room? (y/n):");
            String input1=sc.next();
            if(input1.equals("y") || input1.equals("yes")){
                System.out.println("taking you towards booking your room ...........");
                reserve(check_in, ch);
            }else{
                System.out.println("taking you to main menu...........");
                menu();
            }

        }else{
            System.out.println("taking you to main menu...........");
            menu();
        }
    }

    public void seeCustomerReservation(){
        boolean choice=true;
        int cnt=3;
        while(choice){
            System.out.println("Please enter your email");
            Scanner s=new Scanner(System.in);
            String email=s.next();
            System.out.println("Loading customer's reservations.................");
            if(h.getCustomer(email)!=null){
                if(h.getCustomersReservations(email).isEmpty()){
                    System.out.println("No customer reservations found!!!!!!!!!");
                    choice=false;
                }else{
                    System.out.println(h.getCustomersReservations(email));
                    choice=false;
                }
            }else{
                System.out.println("customer email not found please try again");
                if(cnt==0){
                    return;
                }
                cnt--;
            }
        
        }

    }

    public void createAccount(){
        while(true){
        System.out.println("Please enter your first name : ");
        Scanner sc=new Scanner(System.in);
        String first=sc.next();
        System.out.println("Please enter your Last name : ");
        String last=sc.next();
        System.out.println("Please enter your email : "+"email should be of the form abc@email.com");
        String email=sc.next();
         try{
            h.createACustomer(email, first, last);
            System.out.println("Account created successfully!");
            break;
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        }
    }

    public void admin(){
        while(true){
            System.out.println("Admin menu");
            System.out.println("-------------------------------------------");
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations ");
            System.out.println("4. Add a room");
            System.out.println("5. Back to main menu");

            Scanner s=new Scanner(System.in);
            String c=s.next();
            switch (c) {
                case "1":
                    System.out.println(a.getAllCustomers());
                    menu();
                case "2":
                    System.out.println(a.getAllRooms());
                    menu();
                case "3":
                    a.displayAllReservations();
                    menu();
                case "4":
                    addRoom();
                case "5":
                    menu();
                default:
                    System.out.println("please enter valid input!!!!");
                    break;
            }
        }
    }

    public void addRoom(){
        boolean check=true;
        while(check){
            System.out.println("Do you want to add a free room");
            Scanner s=new Scanner(System.in);
            String choice=s.next();
            if(choice.toLowerCase().equals("y") || choice.toLowerCase().equals("yes")){
                freeR();
                check=false;
            }else if(choice.toLowerCase().equals("n") || choice.toLowerCase().equals("no")){
                System.out.println("Please enter the room no.");
                String room_no=s.next();
                System.out.println("please add a room price");
                String pr=s.next(); 
                Double price=Double.parseDouble(pr);
                if(price>=0){
                    System.out.println("please enter the room type"+"Single/Double");
                    String rt=s.next();
                    if(rt.toLowerCase().equals("single")){
                        IRoom r = new Room(room_no, price,RoomType.Single);
                        a.addARoom(r);
                        check=false;
                    }else if(rt.toLowerCase().equals("double")){
                        IRoom r = new Room(room_no, price,RoomType.Double);
                        a.addARoom(r);
                        check=false;
                    }else{
                        System.out.println("please enter the valid input");

                    }
                }else{
                    System.out.println("price is always positive!! please enter valid price");
                }
            
        }
    }


    }


    public void reserve(LocalDate checkin,LocalDate checkout){
        System.out.println("Do you have account with us");
        Scanner sc=new Scanner(System.in);
        String input=sc.next();
        if(input.equals("y") || input.equals("yes")){
            System.out.println("please enter your email");
            String email=sc.next();
            if(h.getCustomer(email)!=null){
                Customer c=h.getCustomer(email);
                System.out.println("please enter the Room no. :");
                String s=sc.next();
                IRoom r=h.getRoom(s);
                Reservation res = h.bookARoom(email, r, checkin, checkout);

                if(res == null){
                    System.out.println("Room already reserved!!!!!!!!!!");
                }else{
                    System.out.println("Reservation successful:");
                    System.out.println(res);
                }
            }else{
                System.out.println("email not found please try again");
                reserve(checkin, checkout);
            }
        }else if(input.equals("n") || input.equals("no")){
            createAccount();;                       
        }else{
            System.out.println("Please choose y/yes or n/no");
            reserve(checkin, checkout);
        }
    }

    public void freeR(){
        int check=5;
        while(check>0){
            System.out.println("please select the room no.");
            Scanner sc=new Scanner(System.in);
            String roomNum=sc.next();
            System.out.println("please select the room type");
            String type=sc.next();
            if(type.toLowerCase().equals("single")){
                FreeRoom r=new FreeRoom(roomNum, 0, RoomType.Single);
                a.addARoom(r);
                
            }else if(type.toLowerCase().equals("double")){
                FreeRoom r=new FreeRoom(roomNum, 0, RoomType.Double);
                a.addARoom(r);
            }else{
                System.out.println("please enter the valid input");
            }
        }
    }

    public static LocalDate date_validator(String date){
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            return LocalDate.parse(date,format);
        }catch(DateTimeParseException e){
            return null;
        }

    }

    public static void main(String[] args) throws ParseException {
         MainMenu mainMenu = new MainMenu();

        // call the menu
        mainMenu.menu();
    }
}
