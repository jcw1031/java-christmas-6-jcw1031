package christmas.controller;

import christmas.domain.PromotionSystem;
import christmas.dto.BadgeDto;
import christmas.dto.DiscountsDto;
import christmas.dto.GiveawayMenuDto;
import christmas.dto.OrderMenusDto;
import christmas.exception.IllegalReservationException;
import christmas.exception.InputErrorMessages;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;
import java.util.Optional;

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
        repeatExecution(this::orderMenus);
        printEventBenefits();
    }

    private void reserveVisitDate() {
        int reservationDay = inputView.inputReservationDay();
        promotionSystem.reserveVisitDate(reservationDay);
    }

    private void orderMenus() {
        OrderMenusDto orderMenusDto = inputView.inputOrderMenus();
        promotionSystem.orderMenus(orderMenusDto);
    }

    private void repeatExecution(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalReservationException exception) {
            System.out.println(InputErrorMessages.of(exception.getErrorSubject(), exception.getAdditionalMessage()));
            repeatExecution(runnable);
        }
    }

    private void printEventBenefits() {
        LocalDate visitDate = promotionSystem.getVisitDate();
        outputView.printEventBenefitsMessage(visitDate);

        printOrderMenus();
        printTotalOrderAmount();
        printGiveawayMenu();
        printDiscounts();
        printTotalBenefitsAmount();
        printPaymentAmountAfterDiscount();
        printEventBadge();
    }

    private void printOrderMenus() {
        OrderMenusDto orderMenus = promotionSystem.generateOrderMenusHistory();
        outputView.printOrderMenus(orderMenus);
    }

    private void printTotalOrderAmount() {
        int totalOrderAmount = promotionSystem.getTotalOrderAmount();
        outputView.printTotalOrderAmount(totalOrderAmount);
    }

    private void printGiveawayMenu() {
        Optional<GiveawayMenuDto> giveawayMenu = promotionSystem.determineGiveawayMenu();
        giveawayMenu.ifPresentOrElse(
                outputView::printGiveawayMenu,
                outputView::printGiveawayMenuNone
        );
    }

    private void printDiscounts() {
        Optional<DiscountsDto> discounts = promotionSystem.calculateDiscounts();
        discounts.ifPresentOrElse(
                outputView::printDiscounts,
                outputView::printDiscountsNone
        );
    }

    private void printTotalBenefitsAmount() {
        int totalBenefitsAmount = promotionSystem.getTotalBenefitsAmount();
        outputView.printTotalBenefitsAmount(totalBenefitsAmount);
    }

    private void printPaymentAmountAfterDiscount() {
        int paymentAmountAfterDiscount = promotionSystem.getPaymentAmountAfterDiscount();
        outputView.printPaymentDiscountAfterDiscount(paymentAmountAfterDiscount);
    }

    private void printEventBadge() {
        Optional<BadgeDto> eventBadge = promotionSystem.getEventBadge();
        eventBadge.ifPresentOrElse(
                outputView::printBadge,
                outputView::printBadgeNone
        );
    }
}
