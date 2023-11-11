package christmas;

import christmas.domain.ReservationSystem;
import christmas.domain.reservation.Reservation;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner(
                new OutputView(),
                new InputView(),
                new ReservationSystem(new Reservation())
        );
        eventPlanner.start();
    }
}
