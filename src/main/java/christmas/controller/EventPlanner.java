package christmas.controller;

import christmas.domain.PromotionSystem;
import christmas.exception.IllegalInputException;
import christmas.exception.InputErrorMessages;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlanner {

    private final OutputView outputView;
    private final InputView inputView;
    private final PromotionSystem promotionSystem;

    public EventPlanner(OutputView outputView, InputView inputView, PromotionSystem promotionSystem) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.promotionSystem = promotionSystem;
    }

    public void start() {
        outputView.printStartMessage();
        repeatExecution(this::reserveVisitDate);
    }

    private void reserveVisitDate() {
        int reservationDay = inputView.inputReservationDay();
        promotionSystem.reserveVisitDate(reservationDay);
    }

    private void repeatExecution(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalInputException exception) {
            System.out.println(InputErrorMessages.of(exception.getSubject()));
            repeatExecution(runnable);
        }
    }
}
