package homeworkHotelSupport;

class HomeStay extends Hotel {

    private int upperLimit = 10;
    private int nowAddNum;
    private HomeStayRoom[] newRoom = new HomeStayRoom[upperLimit];

    public HomeStay(String homestayName) {
        this.homestayName = homestayName;
        this.nowAddNum = 0;
    }

    public boolean addRoom(String roomID) {
        if (nowAddNum < upperLimit) {
            newRoom[nowAddNum] = new HomeStayRoom(roomID);
            nowAddNum++;
            return true;
        } else {
            return false;
        }
    }

    public boolean reserve(int roomNum, String customerName) {
        if (!newRoom[roomNum].isIsReserved()) {
            newRoom[roomNum].customerName = customerName;
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
            if (!newRoom[roomNum].customerName.equals(customerName)) {
                return 1;//f
            } else {
                newRoom[roomNum].numOfGuests = numOfGuests;
                newRoom[roomNum].setIsCheckIn(true);
                return 0;//t
            }
        } else {
            newRoom[roomNum].numOfGuests = numOfGuests;
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
        for (int count = 0; count < nowAddNum; count++) {
            if (!newRoom[count].isIsCheckIn() && !newRoom[count].isIsReserved()) {
                result += newRoom[count].roomID + "尚是空房。\n";
            }
        }
        if (result.isEmpty()) {
            result = homestayName + "飯店尚無空房。\n";
        }
        return result;
    }

    public String getUnCheckInRooms() {
        String result = "";
        for (int count = 0; count < nowAddNum; count++) {
            if (newRoom[count].isIsReserved() && !newRoom[count].isIsCheckIn()) {
                result += "預訂" + newRoom[count].roomID + "房的" + newRoom[count].customerName + "還沒入住。\n";
            }
        }
        if (result.isEmpty()) {
            result = homestayName + "飯店無人訂房。\n";
        }
        return result;
    }

    public int getNowAddNum() {
        return nowAddNum;
    }

    public String getRoomList() {
        String result = "";
        for (int count = 0; count < nowAddNum; count++) {
            result += "  < 房號 " + count + " > " + newRoom[count].roomID + " 房\n";
        }
        return result;
    }
    
}
