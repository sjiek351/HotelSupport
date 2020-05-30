package homeworkHotelSupport;

public class TestHomeStay {

    private int upperLimit = 5;
    private int nowAddNum;
    private HomeStay allHomeStays[] = new HomeStay[upperLimit];
    private String print = "";

    public TestHomeStay() {
        this.nowAddNum = 0;
    }

    public void addHotel(int hotelCode, String hotelName) {
        allHomeStays[hotelCode] = new HomeStay(hotelName);
        nowAddNum++;
    }

    public void addRoom(int homestayNum, String roomID) {
        if (allHomeStays[homestayNum].addRoom(roomID)) {
            int roomcode = allHomeStays[homestayNum].getNowAddNum() - 1;
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

    public String callList() {
        String hotelList = "";
        for (int count = 0; count < nowAddNum; count++) {
            hotelList += "[ 飯店代號 " + count + " ] " + allHomeStays[count].homestayName + " 飯店\n" + allHomeStays[count].getRoomList() + "\n";
        }
        return hotelList;
    }

    public String getPrint() {
        return print;
    }

    public String getEmptyRooms(int hotelCode) {
        return allHomeStays[hotelCode].getEmptyRooms();
    }

    public String getUnCheckInRooms(int hotelCode) {
        return allHomeStays[hotelCode].getUnCheckInRooms();
    }

}
