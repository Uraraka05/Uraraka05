/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

/**
 *
 * @author madhu
 */
import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;

class Movie {
    private String name;
    private int duration; // in minutes

    public Movie(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Movie: " + name + ", Duration: " + duration + " minutes";
    }
}

class Show {
    private Movie movie;
    private String showTime;
    private List<Seat> seats;

    public Show(Movie movie, String showTime, int numberOfSeats) {
        this.movie = movie;
        this.showTime = showTime;
        this.seats = new ArrayList<>();
        for (int i = 0; i < numberOfSeats; i++) {
            seats.add(new Seat(i + 1));
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public String getShowTime() {
        return showTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getSeat(int seatNumber) {
        if (seatNumber > 0 && seatNumber <= seats.size()) {
            return seats.get(seatNumber - 1);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Show: " + movie.getName() + " at " + showTime;
    }
}

class Seat {
    private int number;
    private boolean isBooked;

    public Seat(int number) {
        this.number = number;
        this.isBooked = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        if (!isBooked) {
            isBooked = true;
        } else {
            throw new IllegalStateException("Seat is already booked");
        }
    }

    @Override
    public String toString() {
        return "Seat " + number + " is " + (isBooked ? "booked" : "available");
    }
}

class Booking {
    private Show show;
    private List<Seat> bookedSeats;

    public Booking(Show show) {
        this.show = show;
        this.bookedSeats = new ArrayList<>();
    }

    public void bookSeat(int seatNumber) {
        Seat seat = show.getSeat(seatNumber);
        if (seat != null && !seat.isBooked()) {
            seat.book();
            bookedSeats.add(seat);
            System.out.println("Booked seat " + seatNumber);
        } else {
            System.out.println("Seat " + seatNumber + " is not available.");
        }
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getBookedSeats() {
        return bookedSeats;
    }
}


public class TicketBooking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner scanner = new Scanner(System.in);
        
        Movie movie = new Movie("The Matrix", 136);
        Show show = new Show(movie, "7:00 PM", 10);
        Booking booking = new Booking(show);

        while (true) {
            System.out.println("Movie Ticket Booking System");
            System.out.println("1. View Show Details");
            System.out.println("2. View Seats");
            System.out.println("3. Book a Seat");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(show);
                    break;
                case 2:
                    for (Seat seat : show.getSeats()) {
                        System.out.println(seat);
                    }
                    break;
                case 3:
                    System.out.print("Enter seat number to book: ");
                    int seatNumber = scanner.nextInt();
                    booking.bookSeat(seatNumber);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
