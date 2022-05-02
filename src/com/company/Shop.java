package com.company;

import java.util.Date;
import java.util.Objects;

import static java.lang.System.*;

public class Shop {
    private boolean IsDel = false;
    private final String Item;
    private final double Price;
    private final Date ArrivingDate;
    private final int Count;
    private final int Id;

    private Shop Next;

    Shop(String name, Date arrivingDate, int price, int count, int id) {
        Item = name;
        Price = price;
        ArrivingDate = (Date) arrivingDate.clone();
        Count = count;
        Id = id;
    }

    public Shop pushToStart(String name, Date arrivingDate, int price, int count, int id) {
        Shop newItem = new Shop(name, arrivingDate, price, count, id);
        newItem.Next = this;

        return newItem;
    }

    public void pushToEnd(String name, Date arrivingDate, int price, int count, int id) {
        if (Next != null)
            Next.pushToEnd(name, arrivingDate, price, count, id);
        else {
            Next = new Shop(name, arrivingDate, price, count, id);
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
                    if (arrivingDate.equals(current.ArrivingDate))
                        this.yield(current);

                    current = current.Next;
                }
            }
        };
    }

    public Double getPriceByName(String name) {
        if (Objects.equals(Item, name))
            return Price * Count * 1.13;
        else if (Next != null)
            return Next.getPriceByName(name);
        else
            return null;
    }

    public void printItem() {
        if (IsDel)
            return;

        out.println("Название товара: " + Item + " Кол-во: " + Count + " Дата прибытия: " + ArrivingDate + " Цена: " + Price);
        out.println();
    }

    public boolean delById(int id) {
        if (Id == id && !IsDel) {
            IsDel = true;
            return true;

        } else if (Next != null && Next.Id == id) {
            Next = Next.Next;
            return true;

        } else if (Next != null)
            return Next.delById(id);

        else
            return false;
    }
}
