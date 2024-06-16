package com.nhathuy.calculator.presenter;

import com.nhathuy.calculator.model.Calculator;
import com.nhathuy.calculator.view.CalculatorView;

import javax.inject.Inject;

public class CalculatorPresenter {
    private CalculatorView view;
    private Calculator calculator;

    @Inject
    public CalculatorPresenter(Calculator calculator){
        this.calculator=calculator;
    }
    public void attachView(CalculatorView view){
        this.view=view;
    }
    public void detachView(){
        this.view=null;
    }

    public void evaluate(String formula){
        try{
            String parts[] =formula.split(" ");
            if(parts.length<3){
                view.showError("Invalid input");
                return;
            }
            double a=Double.parseDouble(parts[0]);
            String operator=parts[1];
            double b=Double.parseDouble(parts[2]);

            double result=0;

            switch (operator){
                case "+":
                    result=calculator.add(a,b);
                    break;
                case "-":
                    result=calculator.subtract(a,b);
                    break;
                case "*":
                    result= calculator.multiply(a,b);
                    break;
                case "/":
                    result=calculator.divide(a,b);
                    break;
                default:
                    view.showError("Unknown operator");
                    return;
            }
        }
        catch (NumberFormatException e){
            view.showError("Invalid number format");
        }
        catch (Exception e){
            view.showError(e.getMessage());
        }
    }


}
