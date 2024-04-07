package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  protected String venueName;
  protected String venueCode;
  private int capacity;
  private int hireFee;

  private ArrayList<Booking> bookingsList = new ArrayList<Booking>(); // list of bookings for venue

  private String nextAvailableDate;

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
    for (Booking booking : bookingsList) {
      if (booking.getBookingDate().equals(bookingDate)) {
        return false; // unavailable
      }
    }
    return true; // available
  }

  public void addBooking(Booking bookingDate) {
    this.bookingsList.add(bookingDate);
  }

  public ArrayList<Booking> getBookings() {
    return bookingsList;
  }

  public void updateNextAvailableDate(String systemDate) {
    this.nextAvailableDate = systemDate;
    String parsedNAD[] = nextAvailableDate.split("/");

    for (Booking booking : bookingsList) {
      if (nextAvailableDate.equals(booking.getBookingDate())) {
        Integer nextDay = Integer.parseInt(parsedNAD[0]) + 1;
        parsedNAD[0] = nextDay.toString();
        if (nextDay < 10) {
          this.nextAvailableDate = "0" + parsedNAD[0] + "/" + parsedNAD[1] + "/" + parsedNAD[2];
        } else {
          this.nextAvailableDate = parsedNAD[0] + "/" + parsedNAD[1] + "/" + parsedNAD[2];
        }
      }
    }
  }

  public String getNextAvailableDate(String systemDate) {
    updateNextAvailableDate(systemDate);
    return nextAvailableDate;
  }
}
