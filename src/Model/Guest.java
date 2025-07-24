package Model;

public class Guest {
    private String name;
    private String id;
    private String phone;
    private String email;
    private int floor;
    private int room;
    private String status;

    public Guest(String name, String id, String phone, String email,
                 int floor, int room, String status) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.floor = floor;
        this.room = room;
        this.status = status;
    }

    // Getters فقط (لا حاجة لـ Setters لأن البيانات لا تتغير بعد الإنشاء)
    public String getName() { return name; }
    public String getId() { return id; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public int getFloor() { return floor; }
    public int getRoom() { return room; }
    public String getStatus() { return status; }
}