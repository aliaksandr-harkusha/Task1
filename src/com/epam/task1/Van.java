// класс очень перегружен бизнес логикой
package com.epam.task1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Van {
    private double maxCost;
    private double balanceCost;
    private double vanAmount;
    private double balanceAmount;
    private ArrayList<Coffee> van;
    private BufferedReader reader;
    private String readerBuffer;

    public Van(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        van = new ArrayList<>();
    }


    // Инициализация объема и максимальной стоимости
    public void launchVan(){
      System.out.print("Введите объем фургона ");
      vanAmount = Double.parseDouble(enterText());
      System.out.print("Введите максиальную стоимость груза ");
      maxCost = Double.parseDouble(enterText());
      balanceAmount=vanAmount;
      balanceCost=maxCost;

      while (true){
          switch (menu()){
              case 1: addCoffee(); break;
              case 2: showVan(); break;
              case 3: sortVan(); break;
              case 4: searchProduct(); break;
              case 5: deleteProduct(); break;
              case 6: exit(0); break;
              default: System.out.println("Нет такого варианта"); break;
          }
      }

    }

    //Добавление в фургон товаров
    private void addCoffee(){
        Coffee coffee;
        String physicalState;
        String kind;
        double amount;
        double packAmount;
        double cost;
        double weight;
        int count;
        System.out.print("Введите состояние - 1) Зерно  2) Молотый  3) Растворисый в банках  4) Растворимый в пакетах: ");
        physicalState = setPhysicalState(Integer.parseInt(enterText()));
        System.out.print("Введите сорт - 1) Арабика  2) Либерика  3)Робуста :");
        kind = setKind(Integer.parseInt(enterText()));
        System.out.print("Введите объем товара: ");
        amount = Double.parseDouble(enterText());
        System.out.print("Введите объем упаковки: ");
        packAmount = Double.parseDouble(enterText());
        System.out.print("Введите стоимость товара: ");
        cost = Double.parseDouble(enterText());
        System.out.print("Введите вес товара: ");
        weight = Double.parseDouble(enterText());
        System.out.print("Введите количество: ");
        count = Integer.parseInt(enterText());
        for(int i=0; i<count; i++) {
            if((balanceCost-cost)>=0 && (balanceAmount-amount-packAmount)>=0) {
                coffee = new Coffee(physicalState, kind, amount, packAmount, cost, weight);
                van.add(coffee);
                balanceCost-=cost;
                balanceAmount-=(amount+packAmount);
                System.out.println("Товар добавлен");
            }
            else {
                System.out.println("Нельзя добавить товар. Объем фургона или стоимость товара переполнены");
                break;
            }
        }
        System.out.println();
    }

    //Отображение товара
    private void showVanElement(Coffee coffee){
        System.out.print("Состояние: "+coffee.getPhysicalState()+" ||| ");
        System.out.print("Сорт: "+coffee.getKind()+" ||| ");
        System.out.print("Цена: "+coffee.getCost()+" ||| ");
        System.out.print("Вес: "+coffee.getWeight()+" ||| ");
        System.out.print("Цена/Вес: "+coffee.getCost()/coffee.getWeight());
        System.out.println();
    }

    //Метод для вывода всего содержимого фургона
    private void showVan(){
        System.out.println("Изначальный объем: "+vanAmount);
        System.out.println("Доступный объем: "+balanceAmount);
        System.out.println("Максимальная стоимость: "+maxCost);
        System.out.println("Доступная стоимость: "+balanceCost);
        for (Coffee coffee : van) {
            showVanElement(coffee);
        }

    }

    //Сортировка по отношению цена/вес
    private void sortVan(){
        Coffee buff;
        for (int i = 0; i < van.size()-1; i++) {
            for (int j = i+1; j < van.size(); j++) {
                if ((van.get(i).getCost()/van.get(i).getWeight())>(van.get(j).getCost()/van.get(j).getWeight())){
                    buff = van.get(i);
                    van.set(i, van.get(j));
                    van.set(j, buff);
                }
            }
        }
        System.out.println("Отсортировано");
        System.out.println();
    }

    //Поиск в фургоне товаров по параметрам
    private void searchProduct(){
        double min;
        double max;
        String buff;
        System.out.println("Поиск по: 1)Стоимости 2)Весу 3)Суммарному объему 4)Физ. состоянию 5)Сорту ");
        switch (Integer.parseInt(enterText())){
            case 1:
                System.out.println("Введите минимальное значение");
                min= Double.parseDouble(enterText());
                System.out.println("Введите максимальное значение");
                max = Double.parseDouble(enterText());
                for (Coffee coffee :van) {
                    if (coffee.getCost()>=min && coffee.getCost()<=max)
                        showVanElement(coffee);
                }
                break;
            case 2:
                System.out.println("Введите минимальное значение");
                min= Double.parseDouble(enterText());
                System.out.println("Введите максимальное значение");
                max = Double.parseDouble(enterText());
                for (Coffee coffee :van) {
                    if (coffee.getWeight()>=min && coffee.getWeight()<=max)
                        showVanElement(coffee);
                }
                break;
            case 3:
                System.out.println("Введите минимальное значение");
                min= Double.parseDouble(enterText());
                System.out.println("Введите максимальное значение");
                max = Double.parseDouble(enterText());
                for (Coffee coffee :van) {
                    if ((coffee.getAmount()+coffee.getPackAmount())>=min && (coffee.getAmount()+coffee.getPackAmount())<=max)
                        showVanElement(coffee);
                }
                break;
            case 4:
                System.out.print("Введите состояние - 1) Зерно  2) Молотый  3) Растворисый в банках  4) Растворимый в пакетах: ");
                 buff = setPhysicalState(Integer.parseInt(enterText()));
                for (Coffee coffee :van) {
                    if (coffee.getPhysicalState().equals(buff))
                        showVanElement(coffee);
                }
                 break;
            case 5:
                System.out.print("Введите сорт - 1) Арабика  2) Либерика  3)Робуста :");
                buff = setKind(Integer.parseInt(enterText()));
                for (Coffee coffee :van) {
                    if (coffee.getKind().equals(buff))
                        showVanElement(coffee);
                }break;
                default:
                    System.out.println("Ошибка!");
        }

    }

    //Метод для ввода текста
    private String enterText(){
        try{
            readerBuffer = reader.readLine();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        return readerBuffer;
    }

    //Меню для оператора switch
    private int menu(){
        System.out.println("1) Добавить товар");
        System.out.println("2) Вывести содержимое фургона");
        System.out.println("3) Отсортировать содержимое фургона");
        System.out.println("4) Поиск товара по параметрам");
        System.out.println("5) Удалить элемент");
        System.out.println("6) Выход");
        return Integer.parseInt(enterText());
    }

    //Выбор сорта
    private String setKind(int i){
        switch (i){
            case 1: return "Арабика";
            case 2: return "Либерика";
            case 3: return "Робуста";
            default: return "Арабика";
        }
    }

    //Выбор физического состояния
    private String setPhysicalState(int i){
        switch (i){
            case 1: return "Зерно";
            case 2: return "Молотый";
            case 3: return "Растворимый в банке";
            case 4: return "Растворимый в пакетах";
            default: return "Арабика";
        }
    }

    //Удаление продукта из фургона
    private void deleteProduct(){
        int i =0;
        for (Coffee coffee :van) {
            System.out.print(((i++)+1)+") ");
            System.out.print("Цена: "+coffee.getCost()+" ||| ");
            System.out.println("Суммарный объем: "+(coffee.getPackAmount()+coffee.getAmount())+" ||| ");

        }
        System.out.println("Какой товар вы хотите удалить?");
        int index = Integer.parseInt(enterText());
        if(index<=van.size())
        van.remove(van.get(index-1));
        else System.out.println("веден неверный индекс");

    }
}
