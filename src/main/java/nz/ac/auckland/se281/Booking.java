package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;

public class Booking {

  private String bookingDate;
  private String customerEmail;
  private String attendeesCount;
  private String bookingReference;
  private String venueCode;

  private ArrayList<Service> servicesList = new ArrayList<Service>();

  public Booking(
      String venueCode, String bookingDate, String customerEmail, String attendeesCount) {
    this.venueCode = venueCode;
    this.bookingDate = bookingDate;
    this.customerEmail = customerEmail;
    this.attendeesCount = attendeesCount;
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public String getAttendeesCount() {
    return attendeesCount;
  }

  public String getBookingReference() {
    return bookingReference;
  }

  // adding services

  public void addCatering(CateringType cateringType, Catering newCatering) {
    newCatering.bookCatering(cateringType, Integer.parseInt(attendeesCount));
    servicesList.add(newCatering);
  }
}
