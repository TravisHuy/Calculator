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
            formula=formula.replaceAll("\\s+","");
            if(formula.isEmpty()){
                view.showError("Invalid input");
                return;
            }

            if(formula.startsWith("-")||formula.startsWith("+")){
                formula="0"+formula;
            }

            String[] parts=formula.split("(?=[-+*/])|(?<=[-+*/])");

            double result=0;

            if(parts.length>0){
                result=Double.parseDouble(parts[0]);
            }

            for(int i=1;i<parts.length;i+=2){
                String operator=parts[i];
                double operand=Double.parseDouble(parts[i+1]);
                switch (operator){
                    case "+":
                        result=calculator.add(result,operand);
                        break;
                    case "-":
                        result=calculator.subtract(result,operand);
                        break;
                    case "*":
                        result= calculator.multiply(result,operand);
                        break;
                    case "/":
                        if(operand==0){
                            view.showError("division by zero");
                            return;
                        }
                        result=calculator.divide(result,operand);
                        break;
                    default:
                        view.showError("Unknown operator");
                        return;
                }
            }
            view.showResult(Double.parseDouble(String.valueOf(result)));
        }
        catch (NumberFormatException e){
            view.showError("Invalid number format");
        }
        catch (Exception e){
            view.showError(e.getMessage());
        }
    }


}
