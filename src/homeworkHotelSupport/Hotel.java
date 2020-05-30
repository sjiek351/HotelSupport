package homeworkHotelSupport;

public abstract class Hotel {
    String homestayName;
    abstract boolean addRoom(String roomID);
    abstract boolean reserve(int roomNum, String customerName);
    abstract int checkIn(int roomNum, String customerName, int numOfGuests);
    abstract boolean checkOut(int roomNum);
}
