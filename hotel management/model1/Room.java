package model1;

public class Room implements IRoom {
    private final String roomNumber;
    private double price;
    private RoomType roomType;
    private boolean isFree;

    public Room(String roomNumber, double price, RoomType roomType) {
    this.roomNumber = roomNumber;
    this.price = price;
    this.roomType = roomType;
    this.isFree = true;
}
    
    public String getRoomNumber(){
        return roomNumber;
    }
    public double getRoomPrice(){
        return price;
    } 
    public RoomType getRoomType(){
        return roomType;
    }
    public boolean isFree(){
        return isFree;
    }

    public String toString(){
        return "Room Details{"+
                "\n    Room Number : "+roomNumber+
                "\n    Price : "+price+
                "\n    Room Type : "+roomType+
                "\n     }";
    }
}









