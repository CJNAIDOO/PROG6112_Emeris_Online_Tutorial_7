package ticketsales;

/**
 * Interface given in the exam paper (Q.1.2).
 * Any class that implements it must provide all four calculations.
 */
public interface ITicketSales {

    int TotalTicketSales(int[][] ticketSales);

    double AverageTicketSales(int[][] ticketSales);

    int HighestSale(int[][] ticketSales);

    int LowestSale(int[][] ticketSales);
}
