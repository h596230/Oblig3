package Oppgave2;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] tab1 = createTabel(64000, 100000);
        long startTime = System.currentTimeMillis();
        sorteringVedInnsettning(tab1, false);
        long endTime = System.currentTimeMillis();
        System.out.println("Funksjon brukte " + (endTime - startTime) + "ms");

    }

    // n tabbel størrelse, range største tilfeldig tall
    static int[] createTabel(int n, int range) {
        int[] tab = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            tab[i] = random.nextInt(range);
        }
        return tab;
    }

    // print tabel
    static void printTab(int[] t) {
        for (int i = 0; i < t.length; i++) {
            System.out.println(i + 1 + ". " + t[i]);
        }
    }

    // print array av Marting &copy;
    static void printArray(String s, int[] a) {
        String outstring = "";
        for (int i : a) {
            outstring += i + ", ";
        }
        System.out.println(s + outstring.substring(0, outstring.length() - 2));
    }

    // sortering ved innsettning
    static void sorteringVedInnsettning(int[] t, boolean print) {
        for (int i = 1; i < t.length; i++) {
            int current = t[i];//
            int j = i - 1;
            while (j >= 0 && t[j] > current) {
                t[j + 1] = t[j];
                j--;
            }
            t[j + 1] = current;
        }
        if (print)
            printArray("Sortering ved Innsettning ", t);

    }

    // Utvalgssortering
    // https://en.wikipedia.org/wiki/Selection_sort
    static void utvalgsSortering(int[] t, boolean print) {
        for (int i = 0; i < t.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < t.length; j++) {
                if (t[j] < t[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = t[minIndex];
            t[minIndex] = t[i];
            t[i] = temp;
        }
        if (print)
            printArray("Utvalgssortering ", t);
    }

    // Plukksortering
    static void plukkSortering(int[] t, boolean print) {
        int[] newTab = new int[t.length];// hjelpetabell
        for (int i = 0; i < t.length; i++) {
            int minPos = 0;// antar at min er på 0-plassen
            for (int j = 1; j < t.length; j++) {
                if (t[j] < t[i]) {
                    minPos = j;
                }
            }
            newTab[i] = t[minPos];
        }
        if (print)
            printArray("Plukk Sortering ", newTab);
    }

    // Kvikksortering
    // https://www.youtube.com/watch?v=h8eyY7dIiN4&ab_channel=CodingwithJohn
    static void kvikkSortering(int[] t, int lowIndex, int highIndex, boolean print) {
        int pivot = t[highIndex];

        if (print)
            printArray("Kvikk Sortering", t);
    }
    //
}