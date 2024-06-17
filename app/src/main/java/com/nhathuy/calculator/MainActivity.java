package com.nhathuy.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.nhathuy.calculator.di.DaggerAppComponent;
import com.nhathuy.calculator.presenter.CalculatorPresenter;
import com.nhathuy.calculator.view.CalculatorView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements CalculatorView {

    @Inject
    CalculatorPresenter presenter;

    private TextView tvFormula;
    private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvFormula=findViewById(R.id.tvFormula);
        tvResult=findViewById(R.id.tvResult);

        DaggerAppComponent.create().inject(this);

        presenter.attachView(this);

        tvFormula=findViewById(R.id.tvFormula);
        tvResult=findViewById(R.id.tvResult);

        Button btnClear=findViewById(R.id.clear);
        btnClear.setOnClickListener(v->clearInputs());

        Button btnPlus=findViewById(R.id.plus);
        btnPlus.setOnClickListener(v->handleOperation("+"));

        Button btnSubtract= findViewById(R.id.minues);
        btnSubtract.setOnClickListener(v->handleOperation("-"));

        Button btnMultiply=findViewById(R.id.multiply);
        btnMultiply.setOnClickListener(v->handleOperation("*"));

        Button btnDivide=findViewById(R.id.devide);
        btnDivide.setOnClickListener(v->handleOperation("/"));


        Button btnEqual=findViewById(R.id.equal);
        btnEqual.setOnClickListener(v-> evaluate());


        setNumberButtonListener(R.id.one,"1");
        setNumberButtonListener(R.id.two,"2");
        setNumberButtonListener(R.id.three,"3");
        setNumberButtonListener(R.id.four,"4");
        setNumberButtonListener(R.id.five,"5");
        setNumberButtonListener(R.id.six,"6");
        setNumberButtonListener(R.id.seven,"7");
        setNumberButtonListener(R.id.eight,"8");
        setNumberButtonListener(R.id.nine,"9");
        setNumberButtonListener(R.id.zero,"0");

        Button btnDot=findViewById(R.id.dot);
        btnDot.setOnClickListener(v->tvFormula.append("."));


    }

    private void setNumberButtonListener(int buttonId, String number) {
        Button button=findViewById(buttonId);
        button.setOnClickListener(v->tvFormula.append(number));
    }

    private void evaluate() {
        String formula=tvFormula.getText().toString();
        presenter.evaluate(formula);
    }

    private void handleOperation(String operator) {
        String formula=tvFormula.getText().toString();
        if(!formula.isEmpty() && !formula.endsWith(" ")){
            tvFormula.append(" "+operator+" ");
        }
    }

    private void clearInputs() {
        tvFormula.setText("");
        tvResult.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }


    @Override
    public void showResult(double result) {
        tvResult.setText(String.valueOf(result));
    }

    @Override
    public void showError(String error) {
        tvResult.setError(error);
    }


}