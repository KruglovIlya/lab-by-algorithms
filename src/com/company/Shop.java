package com.company;

import java.util.Date;
import java.util.Objects;

import static java.lang.System.*;

public class Shop {
    String Item;
    double Price;
    Date ArrivingDate;
    int Count;
    int Id;

    Shop Next;

    Shop(String name, int price, Date arrivingDate, int count,  int id) {
        Item = name;
        Price = price;
        ArrivingDate = arrivingDate;
        Count = count;
        Id = id;
    }

    public Shop pushToStart(String name, int price, Date arrivingDate, int count, int id) {
        Shop newItem = new Shop(name, price, arrivingDate, count, id);
        newItem.Next = this;

        return newItem;
    }

    public void pushToEnd(String name, int price, Date arrivingDate, int count, int id) {
        if (Next != null)
            Next.pushToEnd(name, price, arrivingDate, count, id);
        else {
            Next = new Shop(name, price, arrivingDate, count, id);
        }
    }

    public Generator<Shop> getAllItems() {
        Shop start = this;

        return new Generator<Shop>() {
            public void run() throws InterruptedException {
                Shop current = start;

                while (current != null) {
                    this.yield(current);

                    current = current.Next;
                }
            }
        };
    }

    public Generator<Shop> getItemsByArrivingDate(Date arrivingDate) {
        Shop start = this;

        return new Generator<Shop>() {
            public void run() throws InterruptedException {
                Shop current = start;

                while (current != null) {
                    if (arrivingDate == current.ArrivingDate)
                        this.yield(current);

                    current = current.Next;
                }
            }
        };
    }

    public Double getPriceByName(String name) {
        if (Objects.equals(Item, name))
            return Price * Count * 0.13;
        else if (Next != null)
            return Next.getPriceByName(name);
        else
            return null;
    }

    public void printItem() {
        out.println("Название товара: " + Item + " Кол-во: " + Count + " Дата прибытия: " + ArrivingDate + " Цена: " + Price);
        out.println();
    }

    /* Сделать удаление по Id */
}
