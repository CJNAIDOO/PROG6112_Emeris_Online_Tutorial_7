package ticketsales;

/**
 * Q.1.2 - Performs the ticket sales calculations.
 * Each method loops through every row (year) and every column (season)
 * of the two-dimensional array.
 */
public class TicketSales implements ITicketSales {

    /**
     * Adds up every value in the 2D array.
     */
    @Override
    public int TotalTicketSales(int[][] ticketSales) {
        int total = 0;
        for (int year = 0; year < ticketSales.length; year++) {
            for (int season = 0; season < ticketSales[year].length; season++) {
                total += ticketSales[year][season];
            }
        }
        return total;
    }

    /**
     * Average = total / number of values.
     * The number of values is counted from the array (not hard-coded as 8),
     * and the division is done as a double so no decimals are lost.
     */
    @Override
    public double AverageTicketSales(int[][] ticketSales) {
        int count = 0;
        for (int[] yearSales : ticketSales) {
            count += yearSales.length;
        }
        if (count == 0) {
            return 0; // avoid dividing by zero on an empty array
        }
        return (double) TotalTicketSales(ticketSales) / count;
    }

    /**
     * Starts with the first value and replaces it whenever a bigger one is found.
     */
    @Override
    public int HighestSale(int[][] ticketSales) {
        int highest = ticketSales[0][0];
        for (int[] yearSales : ticketSales) {
            for (int sale : yearSales) {
                if (sale > highest) {
                    highest = sale;
                }
            }
        }
        return highest;
    }

    /**
     * Starts with the first value and replaces it whenever a smaller one is found.
     */
    @Override
    public int LowestSale(int[][] ticketSales) {
        int lowest = ticketSales[0][0];
        for (int[] yearSales : ticketSales) {
            for (int sale : yearSales) {
                if (sale < lowest) {
                    lowest = sale;
                }
            }
        }
        return lowest;
    }
}
