import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException {
        System.out.println("Please input currency you want to convert from, then currency you want to convert to, then amount of money you want to convert:");
        Scanner input = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<>();
        while (input.hasNextLine()) {
            data.add(input.nextLine());
        }
        if (data.size() > 3) {
            System.out.println("ERROR: too much arguments provided, must be 3");
            return;
        }
        data.set(0, data.get(0).toUpperCase());
        data.set(1, data.get(1).toUpperCase());
        double value;
        try {
            value = Double.parseDouble(data.get(2));
        } catch (NumberFormatException e) {
            System.out.println("ERROR: amount must be given as a number");
            return;
        }
        if (value < 0) {
            System.out.println("ERROR: amount of money must be greater or equal to zero");
            return;
        }
        ExchangeFunction ef = new ExchangeFunction(data.get(0), data.get(1));
        double rate = ef.getExchangeRate();
        String formatedRes = new DecimalFormat("#0.0000").format(value * rate);
        if (rate > 0) {
            System.out.println(data.get(2) + " " + data.get(0) + " is equal to " + formatedRes + " " + data.get(1));
        }
    }
}
