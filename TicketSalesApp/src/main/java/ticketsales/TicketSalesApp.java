package ticketsales;

/**
 * Q.1.1 - Prints the Harbourlight Theatre ticket sales report to the console.
 */
public class TicketSalesApp {

    // Line used to separate the sections of the report
    private static final String DIVIDER = "----------------------------------------------";

    public static void main(String[] args) {

        // Single-dimensional array holding the column headings (season names)
        String[] seasons = {"SUMMER", "AUTUMN", "WINTER", "SPRING"};

        // Two-dimensional array: rows = years, columns = seasons
        int[][] ticketSales = {
            {420, 310, 180, 390},   // Year 1
            {450, 280, 220, 390}    // Year 2
        };

        // Object that does all the calculations (via the interface type)
        ITicketSales sales = new TicketSales();

        // ---------- Heading ----------
        System.out.println("THEATRE TICKET SALES REPORT - 2026");
        System.out.println(DIVIDER);

        // ---------- Column headings (season names) ----------
        System.out.printf("%-9s", "");             // blank space above the YEAR labels
        for (String season : seasons) {
            System.out.printf("%-8s", season);
        }
        System.out.println();

        // ---------- Rows: one row per year ----------
        for (int year = 0; year < ticketSales.length; year++) {
            System.out.printf("%-9s", "YEAR " + (year + 1));
            for (int season = 0; season < ticketSales[year].length; season++) {
                System.out.printf("%-8d", ticketSales[year][season]);
            }
            System.out.println();
        }
        System.out.println(DIVIDER);

        // ---------- Summary (average rounded to the nearest whole number) ----------
        System.out.printf("%-22s%d%n", "Total tickets sold:", sales.TotalTicketSales(ticketSales));
        System.out.printf("%-22s%d%n", "Average per season:", Math.round(sales.AverageTicketSales(ticketSales)));
        System.out.printf("%-22s%d%n", "Highest season:", sales.HighestSale(ticketSales));
        System.out.printf("%-22s%d%n", "Lowest season:", sales.LowestSale(ticketSales));
        System.out.println(DIVIDER);
    }
}
