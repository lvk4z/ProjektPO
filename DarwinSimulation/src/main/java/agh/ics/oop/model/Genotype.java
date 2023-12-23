package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Genotype {

    private final List<Integer> genes = new ArrayList<>();
    Random rand = new Random();

    public Genotype(int n) {
        IntStream.range(0, n).forEach(i -> genes.add(rand.nextInt(7)));
    }

    public List<Integer> getGenes(){
        return genes;
    }
}
