/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 */
package net.gregoriomassara.javaapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the + button is clicked.
     */

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     *
     * @boolean hasWhippedCream checks whether the whipped cream check is ticked or not (true or false state)
     * @boolean hasChocolate checks whether the chocolate check is ticked or not (true or false state)
     */

    public void submitOrder(View view) {
        CheckBox checkedBox = (CheckBox) findViewById(R.id.add_whipped_cream);
        boolean hasWhippedCream = checkedBox.isChecked();

        CheckBox checkedBox2 = (CheckBox) findViewById(R.id.add_chocolate);
        boolean hasChocolate = checkedBox2.isChecked();

        Log.v("MainActivity.java", "Has Whipped Cream!!!!!!!!!!!!!!!!!!!!" + hasWhippedCream);
        Log.v("MainActivity.java", "Has Chocolate!!!!!!!!!!!!!!!!!!!!" + hasChocolate);

        int price = calculatePrice();
        String summaryMessage = createOrderSummary(price, hasWhippedCream, hasChocolate);
        displayMessage(summaryMessage);
    }

    /**
     * This method calculates the price of the order.
     *
     *@return the total price of the coffees
     */

    private int calculatePrice() {
        int price = quantity * 5;
        return price;
    }

    /**
     * This method creates an order summary:
     *
     * @param price is the price of the order
     * @param hasWhippedCream is whether or not the user wants or not whipped cream ;)
     * @param hasChocolate is whether or not the user wants or not chocolate on top!
     *
     * @return text of the summary order!
     */

    private String createOrderSummary (int price, boolean hasWhippedCream, boolean hasChocolate) {
        String customerName = "Name: Lyla The Labyrinth";
        String summary = customerName +  "\nAdd Whipped Cream? " + hasWhippedCream;
        summary += "\nAdd Chocolate? " + hasChocolate;
        summary += "\nPrice: £" + price + "\nThank You!";
        summary += "\nQuantity: " + quantity;
        return summary;
    }

    /**
     * This method displays the given Order Summary on the screen.
     */

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the value of the given quantity of coffees  on the screen.
     */
    private void displayQuantity(int coffeeNumber) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + coffeeNumber);
    }





}