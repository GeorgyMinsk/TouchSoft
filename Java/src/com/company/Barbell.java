package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//Класс который будет высчитывать комбинации
public class Barbell {
    private ArrayList <Integer> diskList = new ArrayList<Integer>();
    //Максимально возможный вес штанги
    private int maxWeight = 0;
    //Массив блинов
    private int[] array;
    //Максимальный найденный вес
    private int result;
    //Количество блинов на штанге
    private int discOnBarbell = 0;
    private int weightLeftDisc,
                weightRightDisc;
    public Barbell (int[] arr){
        maxWeight = Arrays.stream(arr).sum();
        array = new int[arr.length];
        for (int i = 0; i <arr.length ; i++) {
            array[i] = arr[i];
        }
        checkAllDisc();
    }
    //Проверка исходных дисков
    private void checkAllDisc () {
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= 1 | array[i] > 20) {
                System.out.println("Wrong weight value of disc!!!");
            }
        }
    }
    // Проверка готовой штанги
    private void checkBarbell (){
        if (result >10000) {
            System.out.println("Value weight  barbell more 10000.");
        }
        if (discOnBarbell > 1000) {
            System.out.println("The number of discs on the rod is more than 1000.");
        }
    }
    // Вычисление максимально возможного веса (на обоих концах равные половины)
    private void sumOnBarbell(){
        for (int i = 0; i < array.length; i++) {
            diskList.add(array[i]);
        }
        int maxWeightLocal = maxWeight;
        Collections.reverse(diskList);
        for (int i = 0; i < maxWeightLocal; i+=2) {

            if ( weightRightDisc == weightLeftDisc & weightRightDisc == maxWeightLocal/2) {
                break;
            }
            else {
                maxWeightLocal = maxWeight - i;
                weightRightDisc = weightLeftDisc = 0;
                for (Integer disk: diskList) {
                    if (disk + weightRightDisc == maxWeightLocal/2 |
                            Math.abs(weightRightDisc + disk - weightLeftDisc) <= Math.abs(weightLeftDisc + disk - weightRightDisc )) {
                        if (disk + weightRightDisc + diskList.get(diskList.size()-1)> maxWeightLocal/2 & disk + weightRightDisc != maxWeightLocal/2 ) {
                            continue;
                        }
                        else {
                            if (weightRightDisc == maxWeightLocal/2) {
                                continue;
                            }
                            weightRightDisc += disk;
                            discOnBarbell++;
                        }

                    }
                    else {
                        if (disk + weightLeftDisc + diskList.get(diskList.size()-1)> maxWeightLocal/2 & disk + weightLeftDisc != maxWeightLocal/2) {
                            continue;
                        }
                        else {
                            weightLeftDisc +=disk;
                            discOnBarbell++;
                        }

                    }
                }
            }
        }
        if (weightLeftDisc == weightRightDisc) {
            result = weightLeftDisc*2;
        }
    }
    //Вывод результатов
    public int getResult (){
        checkBarbell();
        sumOnBarbell();
        return result;
    }
}
