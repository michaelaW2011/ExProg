package at.sw2017.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class Calculator extends AppCompatActivity implements View.OnClickListener {

    Button buttonC;
    Button buttonResult;
    Button buttonAdd;
    Button buttonMinus;
    Button buttonMul;
    Button buttonDiv;


    ArrayList<Button> numberButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        numberButtons = new ArrayList<Button>();

        setUpNumberButtonListener();

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(this);

        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonDiv.setOnClickListener(this);

        buttonMul = (Button) findViewById(R.id.buttonMul);
        buttonMul.setOnClickListener(this);

        buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);

        buttonResult = (Button) findViewById(R.id.buttonResult);
        buttonResult.setOnClickListener(this);

    }

    public void setUpNumberButtonListener() {
        for(int i = 0; i<= 9; i++)
        {
            String buttonName = "button" + i;

            int id = getResources().getIdentifier(buttonName, "id", R.class.getPackage().getName());

            Button button = (Button) findViewById(id);
            button.setOnClickListener(this);

            numberButtons.add(button);

        }
    }

    public void onClick(View v) {}
}
