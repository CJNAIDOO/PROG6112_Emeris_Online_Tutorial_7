package ticketsales;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Q.1.3 - Unit tests for the TicketSales class.
 * Each test follows Arrange / Act / Assert.
 */
public class TicketSalesTest {

    // Same data used in the report
    private final int[][] ticketSales = {
        {420, 310, 180, 390},
        {450, 280, 220, 390}
    };

    private final TicketSales sales = new TicketSales();

    @Test
    public void CalculateTotalTicketSales_ReturnsTotalTicketSales() {
        // Arrange
        int expected = 2640;

        // Act
        int actual = sales.TotalTicketSales(ticketSales);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void AverageTicketSales_ReturnsAverageTicketSales() {
        // Arrange
        double expected = 330.0;

        // Act
        double actual = sales.AverageTicketSales(ticketSales);

        // Assert - doubles need a delta (allowed difference)
        assertEquals(expected, actual, 0.01);
    }
}
