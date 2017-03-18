package at.sw2017.calculator;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static at.sw2017.calculator.Calculator.State.ADD;
import static at.sw2017.calculator.Calculator.State.DIV;
import static at.sw2017.calculator.Calculator.State.INIT;
import static at.sw2017.calculator.Calculator.State.MUL;
import static at.sw2017.calculator.Calculator.State.SUB;


public class Calculator extends AppCompatActivity implements View.OnClickListener {

    Button buttonC;
    Button buttonResult;
    Button buttonAdd;
    Button buttonMinus;
    Button buttonMul;
    Button buttonDiv;

    TextView numberView;

    ArrayList<Button> numberButtons;

    public enum State {
        ADD, SUB, MUL, DIV, INIT, NUM
    }

    int firstNumber = 0;

    State state = State.INIT;

    private void clearTextView() {
        numberView.setText("0");
        firstNumber = 0;
        state = State.INIT;
    }

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

        numberView = (TextView) findViewById(R.id.textView);

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

  //  public void onClick(View v) {}

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;

        switch (clickedButton.getId()) {
            case R.id.buttonAdd:
                clearNumberView();
                state = State.ADD;
                break;
            case R.id.buttonMinus:
                clearNumberView();
                state = State.SUB;
                break;
            case R.id.buttonMul:
                clearNumberView();
                state = State.MUL;
                break;
            case R.id.buttonDiv:
                clearNumberView();
                state = State.DIV;
                break;
            case R.id.buttonResult:
                calculateResult();
                state = State.INIT;
                break;
            case R.id.buttonC:
                clearTextView();
                break;
            default:
                String recentNumber = numberView.getText().toString();
                if(state == State.INIT) {
                    recentNumber = "";
                    state = State.NUM;
                }
                recentNumber += clickedButton.getText().toString();
                numberView.setText(recentNumber);
        }
    }

    private void clearNumberView() {
        String tempString = numberView.getText().toString();
        if(!tempString.equals("")) {
            firstNumber = Integer.valueOf(tempString);
        }
        numberView.setText("");
    }


    private void calculateResult() {
        int secondNumber = 0;

        String tempString = numberView.getText().toString();
        if(!tempString.equals("")) {
            secondNumber = Integer.valueOf(tempString);
        }

        int result;
        switch(state) {
            case ADD:
                result = Calculations.doAddition(firstNumber, secondNumber);
                break;
            case SUB:
                result = Calculations.doSubtraction(firstNumber, secondNumber);
                break;
            case MUL:
                result = Calculations.doMultiplication(firstNumber, secondNumber);
                break;
            case DIV:
                result = Calculations.doDivision(firstNumber, secondNumber);
                break;
            default:
                result = secondNumber;
        }
        numberView.setText(Integer.toString(result));
    }



}
