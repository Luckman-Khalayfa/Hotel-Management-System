package Model;

public class Room {
    private int number;
    private int floor;
    private String type;
    private double price;
    private boolean occupied;
    private Guest currentGuest;

    public Room(int number, int floor, String type, double price) {
        this.number = number;
        this.floor = floor;
        this.type = type;
        this.price = price;
    }

    // Getters و Setters
    public int getNumber() { return number; }
    public int getFloor() { return floor; }
    public String getType() { return type; }
    public double getPrice() { return price; }
    public boolean isOccupied() { return occupied; }
    public Guest getCurrentGuest() { return currentGuest; }

    public void setOccupied(boolean occupied) { this.occupied = occupied; }
    public void setCurrentGuest(Guest guest) { this.currentGuest = guest; }

    @Override
    public String toString() {
        return "Room " + number + " (Floor " + floor + ") - " + type + " - $" + price + "/night";
    }
}
