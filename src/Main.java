import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {


   /*             Вам нужно будет написать два метода:
        1. public static List<Item> findBestSetOfItems(double maxWeight) – метод должен возвращать список
        (используйте arrayList) объектов класса Item,
                суммарная стоимость которых максимальна, при этом не превышает maxWeight.
        2. public static List<List<Item>> getAllSubsets(List<Item> items) –
        метод возвращает всевозможные комбинации наших вещей в виде списка списков.
        Обратите внимание: List<List<Item> означает список, который хранит списки, которые хранят Item.
        Т.е мы имеем что-то такое:
        {{}, {item1}, {item1, item2}, {item2}, {item3}, {item1, item2, item3}, {item1, item3}}.
    */
        List<Item> items = Arrays.asList(
                new Item(356.4, 10),
                new Item(13.75, 40),
                new Item(136.63, 70),
                new Item(13, 300),
                new Item(130, 200)
        );

        double maxWeight = 200;
        System.out.println("Max cost : " + findMaxCost(items, maxWeight));

        //Вывод списка вещей с макс суммой
        System.out.println("Объекты списка с максимальной суммой " + findBestSetOfItems(items, maxWeight));

        //Вывод списка комбинаций списков вещей
        List<List<Item>> all = getAllSubsets(items);
        System.out.println("Список списков вещей: ");
        for (int i = 0; i < all.size(); i++)
            System.out.println(all.get(i));

    }

    public static double findMaxCost(List<Item> items, double maxWeight) {
        double maxCost = 0.0;
        for (int mask = 0; mask < (1 << items.size()); mask++) {
            double totalCost = 0;
            double totalWeight = 0;
            for (int index = 0; index < items.size(); index++) {
                int value = (mask >> index) & 1;
                if (value == 1) {
                    totalCost += items.get(index).getCost();
                    totalWeight += items.get(index).getWeight();
                }
            }
            if (totalWeight <= maxWeight) {
                maxCost = Math.max(totalCost, maxCost);
            }
        }
        return maxCost;
    }
    //  Вам нужно будет написать два метода:
    //          1. public static List<Item> findBestSetOfItems(double maxWeight) – метод должен возвращать список
    //           (используйте arrayList) объектов класса Item,
    //  суммарная стоимость которых максимальна, при этом не превышает maxWeight.

    public static List<Item> findBestSetOfItems(List<Item> items, double maxWeight) {

        ArrayList<Item> it = new ArrayList<>();

        for (int mask = 0; mask < (1 << items.size()); mask++) {
            double totalWeight = 0;
            for (int i = 0; i < items.size(); i++) {
                int value = (mask >> i) & 1;
                if (value == 1) {
                 //   totalCost += items.get(i).getCost();
                    totalWeight += items.get(i).getWeight();
                }
            }
            if (totalWeight < maxWeight) {
                if (!it.isEmpty()) {
                    it.clear();
                }
                for (int j = 0; j < items.size(); j++) {
                    int value1 = (mask >> j) & 1;
                    if (value1 == 1) {
                        it.add(new Item(items.get(j).getCost(), items.get(j).getWeight()));
                    }
                }
            }
        }
        return it;
    }
    //       2. public static List<List<Item>> getAllSubsets(List<Item> items) –
    //   метод возвращает всевозможные комбинации наших вещей в виде списка списков.
    //   Обратите внимание: List<List<Item> означает список, который хранит списки, которые хранят Item.
//    Т.е мы имеем что-то такое:
//    {{}, {item1}, {item1, item2}, {item2}, {item3}, {item1, item2, item3}, {item1, item3}}.

    public static List<List<Item>> getAllSubsets(List<Item> items) {

        ArrayList<List<Item>> allSubsets = new ArrayList<>();

        for (int mask = 0; mask < (1 << items.size()); mask++) {
            ArrayList<Item> it = new ArrayList<>();
            for (int j = 0; j < items.size(); j++) {
                int value1 = (mask >> j) & 1;
                if (value1 == 1) {
                    it.add(new Item(items.get(j).getCost(), items.get(j).getWeight()));
                }
            }
            allSubsets.add(it);
        }
        return allSubsets;
    }
}
