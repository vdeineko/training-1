package com.ciklum.workshops.SelenidePages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ResultsSelenidePage extends BaseSelenidePage{

    public SelenideElement sortBy = $("#sort");
    public ElementsCollection prices = $$(".sx-price-whole");


    public void sortPage(String text){
        sortBy.selectOption(text);
    }

    public ElementsCollection getPrices(){
        return prices;
    }
}

