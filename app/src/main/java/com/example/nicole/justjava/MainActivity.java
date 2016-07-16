package com.example.nicole.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = numberOfCoffees * 5;
        String priceMessage = "Total: R$" + price + ",00";
        priceMessage = priceMessage + "\n" +
                "Obrigada pelo pedido! :)";
        displayMessage(priceMessage);
    }

    public void increment(View view) {
        numberOfCoffees = numberOfCoffees + 1;
        display(numberOfCoffees);
        displayPrice(numberOfCoffees*5);
    }

    public void decrement(View view) {
        numberOfCoffees = numberOfCoffees - 1;
        if (numberOfCoffees < 0 ) {
            numberOfCoffees = 0;
        }
        display(numberOfCoffees);
        displayPrice(numberOfCoffees*5);
    }

    public void reset(View view) {
        numberOfCoffees = 0;
        display(0);
        displayPrice(0);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    public void displayMessage(String message){
        TextView price = (TextView) findViewById(R.id.price);
        price.setText(message);
    }
}
