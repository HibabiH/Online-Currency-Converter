import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ExchangeFunction {
    String convertFrom;
    String convertTo;

    public ExchangeFunction(String a, String b) {
        convertFrom = a;
        convertTo = b;
    }

    public double getExchangeRate() throws IOException {
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
}
