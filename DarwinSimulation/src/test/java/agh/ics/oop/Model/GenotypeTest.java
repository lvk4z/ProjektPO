package agh.ics.oop.Model;

import agh.ics.oop.Model.Animals.Genotype;
import org.junit.jupiter.api.Test;

class GenotypeTest {

    @Test
    void mutate_normal() {
        Genotype genotype = new Genotype(10,false,4,10);
        System.out.println(genotype.getGenes());
        genotype.mutate();
        System.out.println(genotype.getGenes());
    }

    @Test
    void mutate_with_min_equals_max() {
        Genotype genotype = new Genotype(10,false,10,10);
        System.out.println(genotype.getGenes());
        genotype.mutate();
        System.out.println(genotype.getGenes());
    }

    @Test
    void mutate_with_max_equals_zero() {
        Genotype genotype = new Genotype(10,false,0,0);
        System.out.println(genotype.getGenes());
        genotype.mutate();
        System.out.println(genotype.getGenes());
    }

    @Test
    void mutate_with_min_greater_than_max() {
        Genotype genotype = new Genotype(10,false,10,0);
        System.out.println(genotype.getGenes());
        genotype.mutate();
        System.out.println(genotype.getGenes());
    }

    @Test
    void mutate_with_swap() {
        Genotype genotype = new Genotype(10,true,1,2);
        System.out.println(genotype.getGenes());
        genotype.mutate();
        System.out.println(genotype.getGenes());
    }

    @Test
    void mutate_with_swap_and_max_equals_zero() {
        Genotype genotype = new Genotype(10,true,0,0);
        System.out.println(genotype.getGenes());
        genotype.mutate();
        System.out.println(genotype.getGenes());
    }
}