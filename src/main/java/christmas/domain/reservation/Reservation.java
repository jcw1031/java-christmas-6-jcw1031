package christmas.domain.reservation;

public class Reservation {

    private VisitDate visitDate;

    public void createVisitDate(int visitDay) {
        visitDate = new VisitDate(visitDay);
    }
}
