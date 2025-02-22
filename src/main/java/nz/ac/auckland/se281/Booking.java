package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class Booking {

  private String venueCode;
  private String partyDate;
  private String customerEmail;
  private String attendeesCount;

  private String bookingDate;
  private String bookingReference;

  private ArrayList<Service> servicesList = new ArrayList<Service>();

  public Booking(
      String bookingDate,
      String venueCode,
      String partyDate,
      String customerEmail,
      String attendeesCount) {
    this.venueCode = venueCode;
    this.partyDate = partyDate;
    this.customerEmail = customerEmail;
    this.attendeesCount = attendeesCount;

    this.bookingDate = bookingDate;
    this.bookingReference = BookingReferenceGenerator.generateBookingReference();
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getPartyDate() {
    return partyDate;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public String getAttendeesCount() {
    return attendeesCount;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getBookingReference() {
    return bookingReference;
  }

  public int getServicesCost() {

    int servicesCost = 0;

    for (Service service : servicesList) {
      servicesCost += service.getServiceCost();
    }

    return servicesCost;
  }

  public ArrayList<Service> getServicesList() {
    return servicesList;
  }

  // adding services

  public void addCatering(CateringType cateringType) {
    Catering newCatering = new Catering(cateringType, Integer.parseInt(attendeesCount));
    servicesList.add(newCatering);
  }

  public void addMusic() {
    Music newMusic = new Music();
    servicesList.add(newMusic);
  }

  public void addFloral(FloralType floralType) {
    Floral newFloral = new Floral(floralType);
    servicesList.add(newFloral);
  }
}
