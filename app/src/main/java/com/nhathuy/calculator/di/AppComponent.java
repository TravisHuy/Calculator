package com.nhathuy.calculator.di;

import com.nhathuy.calculator.MainActivity;
import com.nhathuy.calculator.model.Calculator;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, CalculatorModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
