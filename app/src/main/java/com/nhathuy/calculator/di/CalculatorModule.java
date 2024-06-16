package com.nhathuy.calculator.di;

import com.nhathuy.calculator.model.Calculator;
import com.nhathuy.calculator.presenter.CalculatorPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CalculatorModule {

    @Provides
    @Singleton
    Calculator provideCalculatorModel(){
        return new Calculator();
    }

    @Provides
    @Singleton
    CalculatorPresenter provideCalculatorPresenter(Calculator calculator){
        return new CalculatorPresenter(calculator);
    }
}
