import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static double getExchangeRate(String convertFrom, String convertTo) throws IOException {
        String urlAddress = "https://www.google.com/finance/quote/" + convertFrom + "-" + convertTo;
        URL url;
        try {
            url = new URL(urlAddress);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        InputStream is = url.openConnection().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        String rateStr = null;
        while ((line = reader.readLine()) != null) {
            if (line.contains("data-last-price=")) {
                rateStr = line.substring(line.indexOf("data-last-price=") + 17, line.indexOf("data-last-price=") + 23);
                break;
            }
        }
        reader.close();
        double rate = -1;
        try {
            rate = Double.parseDouble(rateStr);
        } catch (NullPointerException e) {
            System.out.println("ERROR: no exchange rate found, please check input");
        }
        return rate;
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Please input currency you want to convert from, then currency you want to convert to, then amount of money you want to convert:");
        Scanner input = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            data.add(input.nextLine());
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
        double rate = getExchangeRate(data.get(0), data.get(1));
        String formatedRes = new DecimalFormat("#0.0000").format(value * rate);
        if (rate > 0) {
            System.out.println(data.get(2) + " " + data.get(0) + " is equal to " + formatedRes + " " + data.get(1));
        }
    }
}
