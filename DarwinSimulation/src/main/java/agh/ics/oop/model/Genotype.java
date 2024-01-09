package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Genotype {

    private final List<Integer> genes = new ArrayList<>();
    Random rand = new Random();

    private final boolean mutation;

    public Genotype(int n, boolean mutation) {
        this.mutation = mutation;
        IntStream.range(0, n).forEach(i -> genes.add(rand.nextInt(8)));
    }

    public Genotype(List<Integer> genes, boolean mutation){
        this(0,mutation);
        this.genes.addAll(genes);
    }

    public List<Integer> getGenes(){
        return genes;
    }

    public void mutate(){
        int genesLength = genes.size();
        int mutationNumber = rand.nextInt(genesLength);
        for(int i=0;i<mutationNumber;i++){
            int genNumber = rand.nextInt(genesLength);
            int getAfterMutation = rand.nextInt(8);
            genes.set(genNumber,getAfterMutation);
        }
        float mutationChance = rand.nextFloat();
        if(mutation && mutationChance>0.5){
            int firstGenNumber = rand.nextInt(genesLength);
            int secondGenNumber = rand.nextInt(genesLength);
            Collections.swap(genes,firstGenNumber,secondGenNumber);
        }
        
    }
}
