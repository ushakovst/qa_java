package com.example;

//интерфейс создан для инъекции зависимости в классе Lion
public interface FelineNeeds {
    int getKittens();
    int getKittens(int kittensCount);
}
