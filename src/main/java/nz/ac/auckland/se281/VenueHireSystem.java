package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  private ArrayList<Venue> venueList = new ArrayList<Venue>(); // list of venue codes
  private ArrayList<Booking> bookingList =
      new ArrayList<Booking>(); // list of booking references for all venues

  public String systemDate;

  public VenueHireSystem() {}

  public void printVenues() {

    if (venueList.isEmpty()) { // checking if there are any venues created to print

      MessageCli.NO_VENUES.printMessage();

    } else {

      String[] stringNumbers = {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
      };

      int n = venueList.size();

      // printing the number of venues in specific format
      if (n == 1) {
        MessageCli.NUMBER_VENUES.printMessage("is", stringNumbers[n], "");
      } else if (n > 1 && n < 10) {
        MessageCli.NUMBER_VENUES.printMessage("are", stringNumbers[n], "s");
      } else {
        MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(n), "s");
      }

      // printing the details of each venue in a list
      if (systemDate == null) {
        for (Venue venue : venueList) {
          MessageCli.VENUE_ENTRY.printMessage(
              venue.getVenueName(),
              venue.getVenueCode(),
              Integer.toString(venue.getCapacity()),
              Integer.toString(venue.getHireFee()),
              "");
        }
      } else {
        for (Venue venue : venueList) {
          MessageCli.VENUE_ENTRY.printMessage(
              venue.getVenueName(),
              venue.getVenueCode(),
              Integer.toString(venue.getCapacity()),
              Integer.toString(venue.getHireFee()),
              venue.getNextAvailableDate(systemDate));
        }
      }
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {

    // checking if the inputted venue name is empty
    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
      return;
    }

    // checking if the inputted venue code is already in use
    for (Venue venue : venueList) {
      if (venue.getVenueCode().equals(venueCode)) {
        MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venue.getVenueName());
        return;
      }
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

    // inputs are all valid, creating new venue
    Venue venue =
        new Venue(
            venueName, venueCode, Integer.parseInt(capacityInput), Integer.parseInt(hireFeeInput));
    venueList.add(venue);
    MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
  }

  public void setSystemDate(String dateInput) {
    systemDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    if (systemDate == null) {
      System.out.println("Current system date is not set.");
    } else {
      MessageCli.CURRENT_DATE.printMessage(systemDate);
    }
  }

  public void makeBooking(String[] options) {

    /* options[0] = venue code
     * options[1] = booking date
     * options[2] = customer email
     * options[3] = number of attendees
     */

    Venue venueToBook = null;

    /* checking booking conditions are met */

    // 1. checking system date is set
    if (systemDate == null) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
      return;
    }

    // 2. checking the date is not in the past
    if (isDateInPast(options[1], systemDate)) {
      MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], systemDate);
      return;
    }

    // 3. checking there is at least one venue in the system
    if (venueList.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
      return;
    }

    // 4. checking the venue code exists
    for (Venue venue : venueList) {
      if (venue.getVenueCode().equals(options[0])) {
        venueToBook = venue;
        break;
      }
    }

    if (venueToBook == null) {
      MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
      return;
    }

    // 5. checking if venue is available on the date
    boolean venueAvailability = venueToBook.checkAvailability(options[1]);
    if (venueAvailability == false) {
      MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(
          venueToBook.getVenueName(), options[1]);
      return;
    }

    /* else all conditions are met and booking will be made */

    // adjust attendee count if needed
    int inputAttendees = Integer.parseInt(options[3]);

    if (inputAttendees < (venueToBook.getCapacity() * 0.25)) {
      options[3] = Integer.toString((int) (venueToBook.getCapacity() * 0.25));
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          Integer.toString(inputAttendees),
          options[3],
          Integer.toString(venueToBook.getCapacity()));
    } else if (Integer.parseInt(options[3]) > venueToBook.getCapacity()) {
      options[3] = Integer.toString(venueToBook.getCapacity());
      MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
          options[3],
          Integer.toString(venueToBook.getCapacity()),
          Integer.toString(venueToBook.getCapacity()));
    }

    Booking booking = new Booking(options[0], options[1], options[2], options[3]);

    bookingList.add(booking);
    venueToBook.addBooking(booking);
    venueToBook.updateNextAvailableDate(systemDate);

    MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(
        booking.getBookingReference(),
        venueToBook.getVenueName(),
        booking.getBookingDate(),
        booking.getAttendeesCount());
  }

  public void printBookings(String venueCode) {

    Venue venueToPrint = null;

    for (Venue venue : venueList) {
      if (venue.getVenueCode().equals(venueCode)) {
        venueToPrint = venue;
      }
    }

    // checking if there are any venues created
    if (venueList.isEmpty()) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    }

    // if venue exists, print heading
    if (venueToPrint == null) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
      return;
    } else {
      MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueToPrint.getVenueName());
    }

    // checking if there are any bookings for the venue
    if (venueToPrint.getBookings().isEmpty()) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueToPrint.getVenueName());
      return;
    } else { // print the bookings
      for (Booking booking : venueToPrint.getBookings()) {
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(
            booking.getBookingReference(), booking.getBookingDate());
      }
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {

    // check if booking reference exists
    if (checkBookingReference(bookingReference) == false) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
      return;
    }

    // add catering to booking
    for (Booking booking : bookingList) {
      if (booking.getBookingReference().equals(bookingReference)) {
        Catering newCatering = new Catering();
        booking.addCatering(cateringType, newCatering);
        String cateringName = newCatering.getCateringName();
        MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Catering (" + cateringName + ")" , bookingReference);
        return;
      }
    }
  }

  public void addServiceMusic(String bookingReference) {

    // check if booking reference exists
    if (checkBookingReference(bookingReference) == false) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
      return;
    }
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {

    // check if booking reference exists
    if (checkBookingReference(bookingReference) == false) {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
      return;
    }
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

  // checking if date is in the past
  public boolean isDateInPast(String inputDate, String systemDate) {

    String[] parsedInputDate = inputDate.split("/");
    String[] parsedSystemDate = systemDate.split("/");

    if (Integer.parseInt(parsedInputDate[2]) < Integer.parseInt(parsedSystemDate[2])) {
      return true;
    } else if (Integer.parseInt(parsedInputDate[2]) == Integer.parseInt(parsedSystemDate[2])) {
      if (Integer.parseInt(parsedInputDate[1]) < Integer.parseInt(parsedSystemDate[1])) {
        return true;
      } else if (Integer.parseInt(parsedInputDate[1]) == Integer.parseInt(parsedSystemDate[1])) {
        if (Integer.parseInt(parsedInputDate[0]) < Integer.parseInt(parsedSystemDate[0])) {
          return true;
        }
      }
    }

    return false; // else date is not in the past
  }

  // check if booking reference exists
  public boolean checkBookingReference(String bookingReference) {
    for (Booking booking : bookingList) {
      if (booking.getBookingReference().equals(bookingReference)) {
        return true;
      }
    }
    return false;
  }
}
