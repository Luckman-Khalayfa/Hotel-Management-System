package Model;

import Model.Guest;
import Model.Room;

import java.io.*;
import java.util.*;

public class HotelData {
    // ألوان التصميم
    public static final String PRIMARY_COLOR = "#2c3e50";
    public static final String SECONDARY_COLOR = "#3498db";
    public static final String ACCENT_COLOR = "#e74c3c";
    public static final String LIGHT_COLOR = "#ecf0f1";
    public static final String DARK_COLOR = "#34495e";
    public static final String BUTTON_PRIMARY = "#3498db";
    public static final String BUTTON_SECONDARY = "#2ecc71";
    public static final String BUTTON_ACCENT = "#e74c3c";

    // بيانات المستخدم
    private String username = "admin";     ////tekon
    private String password = "admin123";

    // هياكل البيانات
    private final List<Room> rooms = new ArrayList<>();
    private final List<Guest> guests = new ArrayList<>();
    private final Map<Integer, Room> roomMap = new HashMap<>();

    public HotelData() {
        loadData();
    }





    // الحصول على الألوان
    public String getPrimaryColor() { return PRIMARY_COLOR; }
    public String getSecondaryColor() { return SECONDARY_COLOR; }
    public String getAccentColor() { return ACCENT_COLOR; }
    public String getLightColor() { return LIGHT_COLOR; }
    public String getDarkColor() { return DARK_COLOR; }


    // إنشاء الغرف (فقط إذا لم تكن هناك بيانات محفوظة)
    private void createRooms() {
        for (int i = 1; i <= 20; i++) {
            int floor = (i <= 5) ? 1 : (i <= 10) ? 2 : (i <= 15) ? 3 : 4;
            String type = (i % 4 == 0) ? "Suite" : (i % 3 == 0) ? "Deluxe" : "Standard";
            double price = (type.equals("Suite")) ? 300 : (type.equals("Deluxe")) ? 200 : 150;

            Room room = new Room(i, floor, type, price);
            rooms.add(room);
            roomMap.put(i, room);
        }
    }

    // تسجيل الوصول
    public void checkInGuest(Guest guest, int roomNumber) {
        Room room = roomMap.get(roomNumber);
        if (room != null && !room.isOccupied()) {
            room.setOccupied(true);
            room.setCurrentGuest(guest);
            guests.add(guest);
            saveData();

        }
    }

    // تسجيل المغادرة
    public void checkOutGuest(int roomNumber) {
        Room room = roomMap.get(roomNumber);
        if (room != null && room.isOccupied()) {
            guests.remove(room.getCurrentGuest());
            room.setOccupied(false);
            room.setCurrentGuest(null);
            saveData();
        }
    }

    // حفظ البيانات في ملف
    public void saveData() {
        try (PrintWriter writer = new PrintWriter("hotel_data.txt")) {

            System.out.println("Saving user data: " + username + "," + password);
            writer.println("USER:" + username + "," + password);

            // حفظ الغرف
            for (Room room : rooms) {
                writer.println("ROOM:" + room.getNumber() + "," +
                        room.getFloor() + "," +
                        room.getType() + "," +
                        room.getPrice() + "," +
                        room.isOccupied());
            }

            // حفظ الضيوف
            for (Guest guest : guests) {
                writer.println("GUEST:" + guest.getName() + "," +
                        guest.getId() + "," +
                        guest.getPhone() + "," +
                        guest.getEmail() + "," +
                        guest.getRoom() + "," +
                        guest.getStatus());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // تحميل البيانات من ملف
    public void loadData() {
        File file = new File("hotel_data.txt");

        // إذا الملف غير موجود، أنشئ بيانات أولية
        if (!file.exists()) {
            System.out.println("No saved data, creating initial data...");
            createRooms();
            return;
        }



        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // مسح البيانات الحالية
            rooms.clear();
            guests.clear();
            roomMap.clear();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("USER:")) {
                    String[] data = line.substring(5).split(",");

                        this.username = data[0];
                        this.password = data[1];

                }

                else if (line.startsWith("ROOM:")) {
                    String[] data = line.substring(5).split(",");
                    int number = Integer.parseInt(data[0]);
                    int floor = Integer.parseInt(data[1]);
                    String type = data[2];
                    double price = Double.parseDouble(data[3]);
                    boolean occupied = Boolean.parseBoolean(data[4]);

                    Room room = new Room(number, floor, type, price);
                    room.setOccupied(occupied);
                    rooms.add(room);
                    roomMap.put(number, room);

                } else if (line.startsWith("GUEST:")) {
                    String[] data = line.substring(6).split(",");
                    String name = data[0];
                    String id = data[1];
                    String phone = data[2];
                    String email = data[3];
                    int roomNum = Integer.parseInt(data[4]);
                    String status = data[5];

                    // الحصول على الطابق من الغرفة
                    int floor = 0;
                    if (roomMap.containsKey(roomNum)) {
                        floor = roomMap.get(roomNum).getFloor();
                    }

                    Guest guest = new Guest(name, id, phone, email, floor, roomNum, status);
                    guests.add(guest);

                    // ربط الضيف بالغرفة
                    Room room = roomMap.get(roomNum);
                    if (room != null) {
                        room.setCurrentGuest(guest);
                    }
                }
            }
            System.out.println("Data loaded successfully! Rooms: " + rooms.size() + ", Guests: " + guests.size());

        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
            createRooms(); // إنشاء بيانات أولية في حالة خطأ
        }
    }

    // ========== الدوال المساعدة ==========
    public List<Room> getRooms() { return rooms; }
    public List<Guest> getGuests() { return guests; }

    public List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.isOccupied()) available.add(room);
        }
        return available;
    }

    public List<Room> getOccupiedRooms() {
        List<Room> occupied = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isOccupied()) occupied.add(room);
        }
        return occupied;
    }

    public boolean validateLogin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void changePassword(String username, String newPassword) {
        this.username = username;
        this.password = newPassword;
        saveData();
    }

    // دالة للحصول على اسم المستخدم الحالي
    public String getCurrentUsername() {
        return this.username;
    }

    // دالة للحصول على كلمة السر الحالية (للاستخدام الداخلي فقط)
    public String getCurrentPassword() {
        return this.password;
    }
}