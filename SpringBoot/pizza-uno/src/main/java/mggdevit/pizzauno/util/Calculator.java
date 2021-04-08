package mggdevit.pizzauno.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import mggdevit.pizzauno.entity.Pizza;

@Component
public class Calculator {

//	public Calculator() {
//		// TODO Auto-generated constructor stub
//	}

    public List<Pizza> getCalc(int budget) {
    	if(budget==0) return new ArrayList<Pizza>();
        return List.of(new Pizza("a", 100), new Pizza("b", 300), new Pizza("c", budget));
    }}
