package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<String> venueList = new ArrayList<String>();

  public VenueHireSystem() {

  }

  public void printVenues() {
    if (venueList.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
    } else {
      for (String venue : venueList) {
        System.out.println(venue);
      }
    }
  }

  public void createVenue(
    String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    } 

    if (Integer.parseInt(capacityInput) < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      return;
    } 

    venueList.add(venueCode);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);

    // For using the venue class in the future
    // Venue venue = new Venue(venueName, venueCode, Integer.parseInt(capacityInput), Integer.parseInt(hireFeeInput));
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
