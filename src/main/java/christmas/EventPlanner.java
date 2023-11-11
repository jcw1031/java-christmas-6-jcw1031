package christmas;

import christmas.domain.ReservationSystem;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {

    private final OutputView outputView;
    private final InputView inputView;
    private final ReservationSystem reservationSystem;

    public EventPlanner(OutputView outputView, InputView inputView, ReservationSystem reservationSystem) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.reservationSystem = reservationSystem;
    }

    public void start() {
        outputView.printStartMessage();
        repeatExecution(this::reserveDate);
    }

    private void reserveDate() {
        int reservationDay = inputView.inputReservationDay();
        reservationSystem.reserveDate(reservationDay);
    }

    private void repeatExecution(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            repeatExecution(runnable);
        }
    }
}
