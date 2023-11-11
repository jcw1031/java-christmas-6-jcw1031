package christmas;

import christmas.domain.PromotionSystem;
import christmas.domain.reservation.Reservation;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventPlanner eventPlanner = new EventPlanner(
                new OutputView(),
                new InputView(),
                new PromotionSystem(new Reservation())
        );
        eventPlanner.start();
    }
}
