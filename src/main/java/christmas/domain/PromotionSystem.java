package christmas.domain;

import christmas.domain.benefits.EventBenefits;
import christmas.domain.benefits.Giveaway;
import christmas.domain.benefits.discount.Discounts;
import christmas.domain.reservation.Orders;
import christmas.domain.reservation.Reservation;
import christmas.domain.reservation.VisitDate;
import christmas.dto.DiscountsDto;
import christmas.dto.GiveawayMenuDto;
import christmas.dto.OrderMenuDto;
import christmas.dto.OrderMenusDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PromotionSystem {

    private final Reservation reservation;
    private final EventBenefits eventBenefits;

    public PromotionSystem(Reservation reservation, EventBenefits eventBenefits) {
        this.reservation = reservation;
        this.eventBenefits = eventBenefits;
    }

    public void reserveVisitDate(int reservationDay) {
        reservation.generateVisitDate(reservationDay);
    }

    public void orderMenus(OrderMenusDto orderMenusDto) {
        List<OrderMenuDto> orderMenus = orderMenusDto.orderMenus();
        reservation.generateOrders(orderMenus);
    }

    public LocalDate getVisitDate() {
        VisitDate visitDate = reservation.getVisitDate();
        return visitDate.date();
    }

    public OrderMenusDto generateOrderMenusHistory() {
        return reservation.generateOrderMenusHistory();
    }

    public int getTotalOrderAmount() {
        return reservation.getTotalOrderAmount();
    }

    public Optional<GiveawayMenuDto> determineGiveawayMenu() {
        Optional<Giveaway> giveawayMenu = eventBenefits.generateGiveawayMenu(reservation);
        return giveawayMenu.map(Giveaway::toDto);
    }

    public Optional<DiscountsDto> calculateDiscounts() {
        Optional<Discounts> discounts = eventBenefits.generateDiscounts(reservation);
        return discounts.map(Discounts::toDto);
    }

    public int getTotalBenefitsAmount() {
        return eventBenefits.getTotalAmount();
    }

    public int getPaymentAmountAfterDiscount() {
        Orders orders = reservation.getOrders();
        int orderAmount = orders.getTotalAmount();
        int discountAmount = eventBenefits.getDiscountAmount();
        return orderAmount + discountAmount;
    }
}
