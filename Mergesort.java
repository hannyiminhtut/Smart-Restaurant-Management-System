package com.example.firstproject;

import java.util.ArrayList;
import java.util.List;

public class Mergesort {
    // Sort dishes by name
    public static List<Dishes> sortByName(List<Dishes> dishes) {
        if (dishes.size() <= 1) {
            return dishes;
        }

        int mid = dishes.size() / 2;
        List<Dishes> left = new ArrayList<>(dishes.subList(0, mid));
        List<Dishes> right = new ArrayList<>(dishes.subList(mid, dishes.size()));

        return merge(sortByName(left), sortByName(right), true);
    }

    // Sort dishes by price
    public static List<Dishes> sortByPrice(List<Dishes> dishes) {
        if (dishes.size() <= 1) {
            return dishes;
        }

        int mid = dishes.size() / 2;
        List<Dishes> left = new ArrayList<>(dishes.subList(0, mid));
        List<Dishes> right = new ArrayList<>(dishes.subList(mid, dishes.size()));

        return merge(sortByPrice(left), sortByPrice(right), false);
    }

    // Merge method for sorting
    private static List<Dishes> merge(List<Dishes> left, List<Dishes> right, boolean byName) {
        List<Dishes> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if ((byName && left.get(i).getName().compareTo(right.get(j).getName()) <= 0) ||
                    (!byName && left.get(i).getPrice() <= right.get(j).getPrice())) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            merged.add(left.get(i++));
        }

        while (j < right.size()) {
            merged.add(right.get(j++));
        }

        return merged;
    }

}
