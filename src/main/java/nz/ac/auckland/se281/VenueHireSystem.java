package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venueList = new ArrayList<Venue>();

  public VenueHireSystem() {}

  public void printVenues() {

    if (venueList.isEmpty()) {
      MessageCli.NO_VENUES.printMessage();
    } else {
        String stringNumbers[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        if (venueList.size() == 1) {
          MessageCli.NUMBER_VENUES.printMessage("is", stringNumbers[venueList.size()], "");
        } else if (venueList.size() > 1 && venueList.size() < 10) {
          MessageCli.NUMBER_VENUES.printMessage("are", stringNumbers[venueList.size()], "s");
        } else {
          MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(venueList.size()), "s");
        }
      }

      for (Venue venue : venueList) {
        MessageCli.VENUE_ENTRY.printMessage(venue.getVenueName(), venue.getVenueCode(), Integer.toString(venue.getCapacity()), Integer.toString(venue.getHireFee()), "");
      }

  }

  public void createVenue(
    String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    } 

    // checking if the input capacity is a valid number
   if (isNumberInteger(capacityInput) == false) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    } else if (Integer.parseInt(capacityInput) <= 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
      return;
    } 

    // checking if the input hire fee is a valid number
    if (isNumberInteger(hireFeeInput) == false) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
      return;
    } else if (Integer.parseInt(hireFeeInput) <= 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
      return;
    }

    Venue venue = new Venue(venueName, venueCode, Integer.parseInt(capacityInput), Integer.parseInt(hireFeeInput));
    venueList.add(venue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  
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

  // checking if an entered number is an integer
  public boolean isNumberInteger(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}

