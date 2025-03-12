package com.example;

import java.util.List;

public class Feline extends Animal implements Predator,FelineNeeds {

    private Animal animal;

    public Feline(Animal animal) {
        this.animal = animal;
    }

    public List<String> eatMeat() throws Exception {
        return getFood("Хищник");
    }

    public String getFamily() {
        return "Кошачьи";
    }
    @Override
    public int getKittens() {
        return getKittens(1);
    }
    @Override
    public int getKittens(int kittensCount) {
        return kittensCount;
    }

    //переопределение родительского метода для исключения зависимости (под вопросом?)
    @Override
    public List<String> getFood(String animalKind) throws Exception {
        return animal.getFood(animalKind);
    }
}