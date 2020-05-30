package homeworkHotelSupport;

public class TestHomeStay {

    private int numOfHomeStays = 5;
    private HomeStay allHomeStays[] = new HomeStay[numOfHomeStays];
    private String print = "";
    private int numAddHotel = 0;

    public void addHotel(int hotelCode, String hotelName) {
        allHomeStays[hotelCode] = new HomeStay(hotelName);
        numAddHotel++;
    }

    public void addRoom(int homestayNum, String roomID) {
        if (allHomeStays[homestayNum].addRoom(roomID)) {
            int roomcode = allHomeStays[homestayNum].getNowaddRoomNum() - 1;
            print = "[ 飯店代號 " + homestayNum + " ] " + "< 房號 " + roomcode + " > " + roomID + " 房\n";
        } else {
            print = "超出房間上限!\n";
        }
    }

    public String reserve(int homestayNum, int roomNum, String customerName) {
        if (allHomeStays[homestayNum].reserve(roomNum, customerName)) {
            return "[ 飯店代號 " + homestayNum + " ]" + " < 房號 " + roomNum + " > 預約手續完成。\n";
        } else {
            return "[ 飯店代號 " + homestayNum + " ]" + " < 房號 " + roomNum + " > 已經被人預約了!\n";
        }
    }

    public String checkIn(int homestayNum, int roomNum, String customerName, int numOfGuests) {
        int status = allHomeStays[homestayNum].checkIn(roomNum, customerName, numOfGuests);
        switch (status) {
            case 0:
                return "[ 飯店代號 " + homestayNum + " ]" + " < 房號 " + roomNum + " > " + "入住完成。\n";
            case 1:
                return "[ 飯店代號 " + homestayNum + " ]" + " < 房號 " + roomNum + " > " + "已經被人預約了!\n";
            case 2:
                return " 飯店代號 " + homestayNum + " ]" + " < 房號 " + roomNum + " > " + "已經有他人入住了!\n";
            default:
                return "error!!";
        }
    }

    public String checkOut(int homestayNum, int roomNum) {
        if (allHomeStays[homestayNum].checkOut(roomNum)) {
            return "[ 飯店代號 " + homestayNum + " ]" + " < 房號 " + roomNum + " > " + "退房手續完成。\n";
        } else {
            return "[ 飯店代號 " + homestayNum + " ]" + " < 房號 " + roomNum + " > " + "房間還沒入住!\n";
        }
    }
//

//    public void runAddRoom(int roomCode, String roomName) {
//        addRoom(roomCode, roomName);
//    }
    public String getPrint() {
        return print;
    }

    public String callList() {
        String hotelList = "";
        for (int count = 0; count < numAddHotel; count++) {
            hotelList += "[ 飯店代號 " + count + " ] " + allHomeStays[count].getHomestayName() + " 飯店\n" + allHomeStays[count].getRoomList() +"\n";
        }

        return hotelList;
    }

    public String getEmptyRooms(int hotelCode) {
        return allHomeStays[hotelCode].getEmptyRooms();
    }

    public String getUnCheckInRooms(int hotelCode) {
        return allHomeStays[hotelCode].getUnCheckInRooms();
    }

}

//
class HomeStay {

    private int numOfRooms = 10;   //房間的數量 , 最多 10 間
    private int nowaddRoomNum;    //已設定的房間數量 , 不能超過 numofRooms
    private HomeStayRoom[] newRoom = new HomeStayRoom[numOfRooms];
    private String homestayName;  //飯店的名稱

    public HomeStay(String homestayName) {
        this.homestayName = homestayName;
        this.nowaddRoomNum = 0;
    }

    public boolean addRoom(String roomID) {
        if (nowaddRoomNum < numOfRooms) {
            newRoom[nowaddRoomNum] = new HomeStayRoom(roomID);
            nowaddRoomNum++;
            return true;
        } else {
            return false;
        }
    }

    public boolean reserve(int roomNum, String customerName) {
        if (!newRoom[roomNum].isIsReserved()) {
            newRoom[roomNum].setCustomerName(customerName);
            newRoom[roomNum].setIsReserved(true);
            return true;
        } else {
            return false;
        }
    }

    public int checkIn(int roomNum, String customerName, int numOfGuests) {
        if (newRoom[roomNum].isIsCheckIn()) {
            return 2;//f
        } else if (newRoom[roomNum].isIsReserved()) {
            if (!newRoom[roomNum].getCustomerName().equals(customerName)) {
                return 1;//f
            } else {
                newRoom[roomNum].setNumOfGuests(numOfGuests);
                newRoom[roomNum].setIsCheckIn(true);
                return 0;//t
            }
        } else {
            newRoom[roomNum].setNumOfGuests(numOfGuests);
            newRoom[roomNum].setIsCheckIn(true);
            return 0;//t
        }
    }

    public boolean checkOut(int roomNum) {
        if (newRoom[roomNum].isIsCheckIn()) {
            newRoom[roomNum].setIsCheckIn(false);
            return true;
        } else {
            return false;
        }
    }

    public String getEmptyRooms() {
        String result = "";
        for (int count = 0; count < nowaddRoomNum; count++) {
            if (!newRoom[count].isIsCheckIn() && !newRoom[count].isIsReserved()) {
                result += newRoom[count].getRoomID() + "尚是空房。\n";
            }
        }
        return result;
    }

    public String getUnCheckInRooms() {
        String result = "";
        for (int count = 0; count < nowaddRoomNum; count++) {
            if (newRoom[count].isIsReserved() && !newRoom[count].isIsCheckIn()) {
                result += "預訂" + newRoom[count].getRoomID() + "房的" + newRoom[count].getCustomerName() + "還沒入住。\n";
            }
        }
        if (result.equals("")) {
            result = homestayName + "飯店無人訂房。\n";
        }
        return result;
    }

    public int getNowaddRoomNum() {
        return nowaddRoomNum;
    }

    public String getRoomList() {
        String result = "";
        for (int count = 0; count < nowaddRoomNum; count++) {
            result += "  < 房號 " + count + " > " + newRoom[count].getRoomID() + " 房\n";
        }
        return result;
    }

    public String getHomestayName() {
        return homestayName;
    }

}

//
class HomeStayRoom {

    private String roomID;
    private String customerName;
    private int numOfGuests;
    private boolean isReserved;
    private boolean isCheckIn;

    public HomeStayRoom(String someRoomID) {
        roomID = someRoomID;
        customerName = "";
        numOfGuests = 0;
        isReserved = false;
        isCheckIn = false;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setNumOfGuests(int numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public void setIsCheckIn(boolean isCheckIn) {
        this.isCheckIn = isCheckIn;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean isIsReserved() {
        return isReserved;
    }

    public boolean isIsCheckIn() {
        return isCheckIn;
    }

}
