package homeworkHotelSupport;

class HomeStayRoom extends Room {

    private boolean isReserved;
    private boolean isCheckIn;

    HomeStayRoom(String someRoomID) {
        this.roomID = someRoomID;
        this.customerName = "";
        this.numOfGuests = 0;
        this.isReserved = false;
        this.isCheckIn = false;
    }

    void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    void setIsCheckIn(boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
    }

    boolean isIsReserved() {
        return isReserved;
    }

    boolean isIsCheckIn() {
        return isCheckIn;
    }
    
}
