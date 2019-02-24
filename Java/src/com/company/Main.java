package com.company;

public class Main {

    public static void main(String[] args) {
// Исходный массив
	int[] massDisc = new int[] {3,9,16,4,5,30};
        Barbell first = new Barbell(massDisc);
        System.out.print(first.getResult());


    }
}
