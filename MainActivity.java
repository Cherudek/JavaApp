/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 */
package net.gregoriomassara.javaapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
     * This method is called when the - button is clicked.
     */

    public void decrement(View view) {
        // Toast message to alert we cannoy order less than 1 Coffee!
        if (quantity <= 1) {
            Toast toast = Toast.makeText(getApplicationContext(), "You cannot have less than 1 Coffee!", Toast.LENGTH_SHORT);
            toast.show();
            return;

        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the + button is clicked.
     */

    public void increment(View view) {

        if (quantity >= 10){
     // Toast message to alert we cannot order more than 10 Coffees!
            Toast toast = Toast.makeText(getApplicationContext(), "You cannot have more than 10 Coffees!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity + 1;
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

        //Entry field for the customer name
        EditText name = (EditText) findViewById(R.id.customer_name);
        String customerName = name.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        String summaryMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, customerName);

       //Creates an email Intent to send an order confirmation
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, customerName + "'s Coffee Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT, summaryMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method calculates the price of the order.
     *@return the total price of the coffees
     */

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {

        int basePrice = 5;

/// Adds 1£ to the cost of the coffee
        if (hasWhippedCream) {
            basePrice +=  1;
        }
// Adds 2£ to the cost of the coffee
        if (hasChocolate) {
            basePrice += 2;
        }
// Return the total price for the coffees ordered plus any extra chocolate or whipped cream added to the order
        return quantity * basePrice;
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

    public String createOrderSummary (int price, boolean hasWhippedCream, boolean hasChocolate, String customerName) {
        String customerName1 = "Name: " + customerName;
        String summary = customerName1 +  "\nAdd Whipped Cream? " + hasWhippedCream;
        summary += "\nAdd Chocolate? " + hasChocolate;
        summary += "\nPrice: £" + price + "\nThank You!";
        summary += "\nQuantity: " + quantity;
        return summary;
    }

    /**
     * This method displays the value of the given quantity of coffees  on the screen.
     */
    private void displayQuantity(int coffeeNumber) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + coffeeNumber);
    }





}