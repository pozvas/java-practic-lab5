package org.example;

import java.io.Serializable;
import java.util.*;

public class ClockShop implements Serializable {
    private ArrayList<Clocks> shop;
    public ClockShop() {
        this.shop = new ArrayList<>();
    }
    public void AddClock(Clocks c){
        shop.add(c);
    }
    public void RemoveClock(int index){
        shop.remove(index);
    }
    public int getLength(){return shop.size();}
    public Clocks GetClock(int index){ return shop.get(index);}

    public void SetShop(ArrayList<Clocks> shop) {
        this.shop = shop;
    }

    public String DescExpClock(){
        Comparator<Clocks> compCost = new Comparator<Clocks>() {
            @Override
            public int compare(Clocks o1, Clocks o2) {
                return o1.getCost() - o2.getCost();
            }
        };
        return Collections.max(this.shop, compCost).toString();

    }
    public void SetTime(Hand hand, int value) throws ExceptionIncorrectInput{
        for(Clocks c : shop){
            if(hand == Hand.SECONDS && c.isSeconds()) c.TimeSet(hand, value);
            if(hand != Hand.SECONDS) c.TimeSet(hand, value);
        }
    }
    public String PopularBrand(){
        HashMap<String, Integer> map_brand = new HashMap<>();
        int max = 0;
        for(Clocks c : this.shop){
            if (map_brand.get(c.getBrand()) == null) map_brand.put(c.getBrand(), 1);
            else map_brand.put(c.getBrand(), map_brand.get(c.getBrand()) + 1);
            if(max < map_brand.get(c.getBrand())) max = map_brand.get(c.getBrand());
        }
        for(String str: map_brand.keySet()){
            if(map_brand.get(str) == max) return str;
        }
        return "";
    }
    public String PrintBrands(){
        TreeSet<String> brands = new TreeSet<>();
        for (Clocks c: this.shop){
            brands.add(c.getBrand());
        }
        System.out.println(brands);
        return brands.toString();
    }
    @Override
    public String toString() {
        return "org.example.ClockShop{" +
                "shop=" + shop +
                '}';
    }
    public ListIterator<Clocks> iterator(){
        return shop.listIterator();
    }
}

