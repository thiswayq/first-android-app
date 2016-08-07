package com.example.nicole.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        String email = createOrderSumary(calculatePrice());
        EditText name = (EditText) findViewById(R.id.name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava App order for " + name.getText());
        intent.putExtra(Intent.EXTRA_TEXT, email);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public void increment(View view) {
        numberOfCoffees = numberOfCoffees + 1;
        if(numberOfCoffees > 100){
            numberOfCoffees = 100;
            Toast.makeText(getApplicationContext(), "Muito café faz mal! ):", Toast.LENGTH_SHORT).show();
        }
        display(numberOfCoffees);
    }

    public void decrement(View view) {
        numberOfCoffees = numberOfCoffees - 1;
        if (numberOfCoffees < 1 ) {
            numberOfCoffees = 1;
            Toast.makeText(getApplicationContext(), "Não vai beber nada? ):", Toast.LENGTH_SHORT).show();
        }
        display(numberOfCoffees);
    }

    public void reset(View view) {
        numberOfCoffees = 0;
        display(0);
        CheckBox whipedCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        EditText name = (EditText) findViewById(R.id.name);
        whipedCream.setChecked(false);
        chocolate.setChecked(false);
        name.setText("");
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private boolean hasWhippedCream(){
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        return whippedCream.isChecked();
    }

    private boolean hasChocolate(){
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        return chocolate.isChecked();
    }

    public int calculatePrice(){
        int toppings = 0;

        if (hasWhippedCream()) {
            toppings += 1;
        }
        if (hasChocolate()){
            toppings += 2;
        }
        return (numberOfCoffees * 5) + (numberOfCoffees * toppings);
    }

    public String createOrderSumary(int price){
        EditText view_name = (EditText) findViewById(R.id.name);
        String name = view_name.getText().toString();
        return getString(R.string.order_sum_name, name) +
                "\n" + getString(R.string.has_whipped_cream) + " ? " + hasWhippedCream() +
                "\n" + getString(R.string.has_chocolate) + " ? " + hasChocolate() +
                "\n"+ getString(R.string.quantity) + ": " + numberOfCoffees +
                "\nTotal: R$" + price +
                "\n" + getString(R.string.thank_you);
    }
}
