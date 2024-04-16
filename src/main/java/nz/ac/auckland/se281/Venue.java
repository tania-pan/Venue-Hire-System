package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;

  private ArrayList<Booking> bookingsList = new ArrayList<Booking>(); // list of bookings for venue

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

  public boolean checkAvailability(String partyDate) {
    for (Booking booking : bookingsList) {
      if (booking.getPartyDate().equals(partyDate)) {
        return false; // unavailable
      }
    }
    return true; // available
  }

  public void addBooking(Booking partyDate) {
    this.bookingsList.add(partyDate);
  }

  public ArrayList<Booking> getBookings() {
    return bookingsList;
  }

  public String getNextAvailableDate(String systemDate) {
    String nextAvailableDate = systemDate;
    String parsedNAD[] = nextAvailableDate.split("/");

    boolean reiterate = true;
    while (reiterate) {
      reiterate = false;
      for (Booking booking : bookingsList) {
        if (nextAvailableDate.equals(booking.getPartyDate())) {
          Integer nextDay = Integer.parseInt(parsedNAD[0]) + 1;
          parsedNAD[0] = nextDay.toString();
          if (nextDay < 10) {
            nextAvailableDate = "0" + parsedNAD[0] + "/" + parsedNAD[1] + "/" + parsedNAD[2];
          } else {
            nextAvailableDate = parsedNAD[0] + "/" + parsedNAD[1] + "/" + parsedNAD[2];
          }
          reiterate = true; // reiterate since nextAvailableDate has changed
        }
      }
    }
    return nextAvailableDate;
  }
}
