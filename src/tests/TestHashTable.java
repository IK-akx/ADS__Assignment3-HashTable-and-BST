package tests;

import models.*;

import java.util.Random;

public class TestHashTable {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(11);
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(1000000);
            String name = "Student" + i;

            MyTestingClass key = new MyTestingClass(id);
            Student value = new Student(name);
            table.put(key, value);
        }


        System.out.println("Bucket sizes:");
        for (int i = 0; i < table.getM(); i++) {
            System.out.println("Bucket " + i + ": " + table.getBucketSize(i));
        }
    }
}
