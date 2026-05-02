import java.util.ArrayList;
import java.util.List;

public class Investment {
    public double calculateAvailableToInvest(double totalIncome, double totalExpenses, double budgetAmount) {
        validateNonNegative(totalIncome, "Total income");
        validateNonNegative(totalExpenses, "Total expenses");
        validateNonNegative(budgetAmount, "Budget amount");
        return totalIncome - totalExpenses - budgetAmount;
    }

    public double calculateProjectedGrowth(double principal, double annualRate, int years) {
        validatePrincipalAndYears(principal, years);
        if (annualRate < 0) {
            throw new IllegalArgumentException("Error, annual rate cannot be negative");
        }
        return principal * Math.pow(1 + annualRate, years);
    }

    public double calculateGrowthEarned(double principal, double annualRate, int years) {
        return calculateProjectedGrowth(principal, annualRate, years) - principal;
    }

    public List<Double> createYearlyProjection(double principal, double annualRate, int years) {
        validatePrincipalAndYears(principal, years);
        if (annualRate < 0) {
            throw new IllegalArgumentException("Error, annual rate cannot be negative");
        }

        List<Double> projection = new ArrayList<>();
        for (int year = 0; year <= years; year++) {
            projection.add(principal * Math.pow(1 + annualRate, year));
        }
        return projection;
    }

    private void validatePrincipalAndYears(double principal, int years) {
        validateNonNegative(principal, "Principal");
        if (years < 0) {
            throw new IllegalArgumentException("Error, years cannot be negative");
        }
    }

    private void validateNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException("Error, " + fieldName.toLowerCase() + " cannot be negative");
        }
    }
}
