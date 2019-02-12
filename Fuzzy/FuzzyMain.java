package Fuzzy;

/**
 * @author Percy
 * Reference: http://scarcitycomputing.blogspot.com/2013/04/damerau-levenshtein-edit-distance.html
 */
public class FuzzyMain {

    @SuppressWarnings("Duplicates")
    public static void main(String [] args) {
        FuzzyContext fc = new FuzzyContext();
        FuzzyContext f1 = new FuzzyContext();


        fc.setF(new Levenshtein());
        System.out.println(fc.fuzzy("tomato","potato"));
        System.out.println(fc.fuzzy("ha ha ha ab","ha ha ha ba"));
        System.out.println(fc.fuzzy("a cat","an act"));

        System.out.println("\n");

        fc.setF(new Damerau_Levenshtein());
        System.out.println(fc.fuzzy("tomato","potato"));
        System.out.println(fc.fuzzy("ha ha ha ab","ha ha ha ba"));
        System.out.println(fc.fuzzy("a cat","an act"));

        System.out.println("\n");

        f1.setF(new hiddenScore());
        System.out.println(f1.fuzzy("master","Imp Master"));
        System.out.println(f1.fuzzy("master","Master Jouster"));
        System.out.println(f1.fuzzy("master","Master Swordsmith"));
        System.out.println(f1.fuzzy("master","Master of Disguise"));
        System.out.println(f1.fuzzy("master","Master of Ceremonies"));
        System.out.println(f1.fuzzy("master","Cult Master"));
        System.out.println(f1.fuzzy("master","Cogmaster"));
        System.out.println(f1.fuzzy("master","Houndmaster"));
        System.out.println(f1.fuzzy("master","Stablemaster"));
        System.out.println(f1.fuzzy("master","Quartermaster"));
        System.out.println(f1.fuzzy("master","Cruel Taskmaster"));
        System.out.println(f1.fuzzy("master","Cogmaster's Wrench"));
        System.out.println(f1.fuzzy("master","Ancient Brewmaster"));
        System.out.println(f1.fuzzy("master","Tinkmaster Overspark"));
        System.out.println(f1.fuzzy("master","Injured Blademaster"));
        System.out.println(f1.fuzzy("master","Youthful Brewmaster"));

        System.out.println("\n");

        System.out.println(f1.fuzzy("Dragon","Dragon Egg"));
        System.out.println(f1.fuzzy("Dragon","Dragon Consort"));
        System.out.println(f1.fuzzy("Dragon","Dragon's Breath"));
        System.out.println(f1.fuzzy("Dragon","Dragonhawk Rider"));
        System.out.println(f1.fuzzy("Dragon","Dragonkin Sorcerer"));
        System.out.println(f1.fuzzy("Dragon","Dragonling Mechanic"));
        System.out.println(f1.fuzzy("Dragon","Faerie Dragon"));
        System.out.println(f1.fuzzy("Dragon","Hungry Dragon"));
        System.out.println(f1.fuzzy("Dragon","Young Dragonhawk"));
        System.out.println(f1.fuzzy("Dragon","Bolvar Fordragon"));
    }

}
