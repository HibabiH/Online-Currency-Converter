import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import static java.lang.System.exit;
public class Runner {
    JFrame f;
    JTextArea textArea;
    JTextField amount;
    JTextField curr1;
    JTextField curr2;
    public Runner(){
        f = new JFrame("A JFrame");
        f.setSize(500, 500);
        f.setLocation(300,200);
        textArea = new JTextArea(10, 40);
        curr1 = new JTextField("Enter currency to convert from: ",200);
        curr2 = new JTextField("Enter currency to convert to: ", 200);
        amount = new JTextField("Enter amount: ", 200);
        JButton ExchangeButton = new JButton("Exchange");
        JButton ExitButton = new JButton("Exit");
        curr1.setBounds(0, 330, 200,40);
        curr2.setBounds(0, 380, 200, 40);
        amount.setBounds(300, 350, 200, 40);
        ExchangeButton.addActionListener(e -> printResult());
        ExitButton.addActionListener(e -> {
            f.setVisible(false);
            exit(0);
        });
        f.setVisible(true);
        f.add(BorderLayout.AFTER_LAST_LINE,ExchangeButton);
        f.add(BorderLayout.BEFORE_FIRST_LINE, ExitButton);
        f.add(curr1);
        f.add(curr2);
        f.add(amount);
        f.add(BorderLayout.CENTER, textArea);
    }
    public void printResult(){
        textArea.setText("");
        String currFrom = curr1.getText();
        String currTo = curr2.getText();
        double value;
        try {
            value = Double.parseDouble(amount.getText());
        }catch (NumberFormatException exep){
            textArea.append("ERROR: Amount must be a number");
            return;
        }
        double rate;
        try {
            rate = getExchangeRate(currFrom, currTo);

        } catch (IOException ex) {
            textArea.append("ERROR: couldn't get info");
            return;
        }
        String formatedStr = new DecimalFormat("#0.0000").format(value * rate);
        textArea.append(value + " " + currFrom + " is equal to " + formatedStr + " " + currTo);
    }
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
        String rateStr = "";
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