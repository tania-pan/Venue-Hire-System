package nz.ac.auckland.se281;

public class Venue {
  
  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;

public Venue(String venueName, String venueCode, int capacity, int hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  public String getVenueName() {
    return venueName;
  }

  public void setVenueName(String venueName) {
    this.venueName = venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public void setVenueCode(String venueCode) {
    this.venueCode = venueCode;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public int getHireFee() {
    return hireFee;
  }

  public void setHireFee(int hireFee) {
    this.hireFee = hireFee;
} 
}
