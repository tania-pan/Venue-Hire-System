package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  protected String venueName;
  protected String venueCode;
  private int capacity;
  private int hireFee;

  private ArrayList<String> bookedDates = new ArrayList<String>();

  // private String nextAvailableDate;

  public Venue(String venueName, String venueCode, int capacity, int hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
  }

  public String getVenueName() {
    return venueName;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public int getCapacity() {
    return capacity;
  }

  public int getHireFee() {
    return hireFee;
  }

  public boolean checkAvailability(String bookingDate) {
    for (String date : bookedDates) {
      if (date.equals(bookingDate)) {
        return false; // unavailable
      }
    }
    return true; // available
  }

  public void setBookingDate(String bookingDate) {
    this.bookedDates.add("bookingDate");
  }

  // unsure ????????????????????
  // public String getBookedDate(int index) {
  //   return bookedDates[index];
  // }

  // public String setNextAvailableDate(String nextAvailableDate) {
  //   this.nextAvailableDate = nextAvailableDate;
  //   return nextAvailableDate;
  // }

  // public String getNextAvailableDate() {
  //   return nextAvailableDate;
  // }
}
