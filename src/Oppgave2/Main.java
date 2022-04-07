package Oppgave2;

import java.util.Random;

public class Main {

  public static void main(String[] args) {
    //for å få en tilfeldig tall
    Random tilfeldig = new Random();
    //n = array størrelse, antal = hvor mye ganger skal vi kjøre test, rang maks tall brukt i testen
    int n = 100, antal = 1, range = 100000;
    int[][] testTab = new int[antal][n];
    //føler test tabeler
    for (int i = 0; i < antal; i++) {
      for (int j = 0; j < n; j++) {
        testTab[i][j] = tilfeldig.nextInt(range);
      }
    }
    //tidsmåling start
    long start = System.currentTimeMillis();
    //gjenomfør sortering
    for (int[] tab : testTab) {
      //sorterings algoritme brukt i testen
      kvikkSortering(tab);
    }
    //tidsmåling slutt
    long end = System.currentTimeMillis();
    System.out.println(end - start + "ms");

  }

  // n tabbel størrelse, range største tilfeldig tall
  public static int[] createTabel(int n, int range) {
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

  // print array av Martin &copy;
  static void printArray(int[] a) {
    printArray("", a);
  }

  static void printArray(String s, int[] a) {
    String outstring = "";
    for (int i : a) {
      outstring += i + ", ";
    }
    System.out.println(s + outstring.substring(0, outstring.length() - 2));
  }

  // sortering ved innsettning nr1
  static void sorteringVedInnsettning(int[] t) {
    for (int i = 1; i < t.length; i++) {
      int temp = t[i];
      int j = i - 1;
      while (j >= 0 && t[j] > temp) {
        t[j + 1] = t[j];
        j--;
      }
      t[j + 1] = temp;
    }
  }

  // Utvalgssortering
  static void utvalgsSortering(int[] t) {
    for (int i = 0; i < t.length - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < t.length; j++) {
        if (t[j] < t[minIndex]) {
          minIndex = j;
        }
      }
      //bruker temp for å ikke miste verdi i tabellen,
      int temp = t[minIndex];
      t[minIndex] = t[i];
      t[i] = temp;
    }
  }

  // Plukksortering
  static void plukkSortering(int[] tab) {
    int[] ntab = new int[tab.length];
    for(int i =0;i  <tab.length;i++){
      int minPos = 0;
      for(int j = 1;j < tab.length;j++){
        if(tab[j] < tab[minPos]){
          minPos = j;
        }
      }
      ntab[i] = tab[minPos];
      //den min blir aldri pluket igjen
      tab[minPos] = Integer.MAX_VALUE;
    }
    for(int i = 0;i < tab.length;i++){
      tab[i] = ntab[i];
    }
  }

  //Kvikksortering
  //default verdi
  static void kvikkSortering(int[] t) {
    kvikkSortering(t, 0, t.length - 1);
  }
  static void kvikkSortering(int[] tab, int lavIndex, int hoyIndex) {
    //stopp hvis tab har bare en element
    if (lavIndex >= hoyIndex) return;

    //tall vi skal samenligne andre tall med
    int pivot = tab[hoyIndex];

    int venstreIndikator = lavIndex;
    int hoyreIndikator = hoyIndex;
    while (venstreIndikator < hoyreIndikator) {
      //while loop for venstre
      while (
        tab[venstreIndikator] <= pivot && venstreIndikator < hoyreIndikator
      ) {
        venstreIndikator++;
      }
      //while lopp for høyre
      while (
        tab[hoyreIndikator] >= pivot && hoyreIndikator > venstreIndikator
      ) {
        hoyreIndikator--;
      }
      bytt(tab, venstreIndikator, hoyreIndikator);
    }
    bytt(tab, venstreIndikator, hoyIndex);
    //rekursive
    kvikkSortering(tab, lavIndex, venstreIndikator - 1);
    kvikkSortering(tab, venstreIndikator + 1, hoyIndex);
  }

  //bytt metode for kviksortering
  static void bytt(int[] tab, int i1, int i2) {
    int temp = tab[i1];
    tab[i1] = tab[i2];
    tab[i2] = temp;
  }

  //fletteSortering
  static void fletteSortering(int[] tab) {
    int tabLengde = tab.length;
    //om vi har en tab med bare 1 element return
    if (tabLengde < 2) return;
    //
    int midIndeks = tabLengde / 2;

    int[] venstreHalv = new int[midIndeks];
    int[] hoyreHalv = new int[tabLengde - midIndeks];
    //fyler opp venstreHalv
    for (int i = 0; i < midIndeks; i++) {
      venstreHalv[i] = tab[i];
    }
    //fyler opp hoyreHalv
    for(int i = midIndeks; i < tabLengde; i++){
      hoyreHalv[i - midIndeks] = tab[i];
    }

    //rekursive
    fletteSortering(venstreHalv);
    fletteSortering(hoyreHalv);
    //s
    merge(tab, venstreHalv, hoyreHalv);
  }
  //slåSammen
  static void merge(int[] tab,  int[] venstreHalv, int[] hoyreHalv){
    int venstreS = venstreHalv.length;
    int hoyreS = venstreHalv.length;
    //i for venstre, j for høyre, k for tab
    int i = 0;
    int j = 0;
    int k = 0;

    //
    while(i < venstreS && j < hoyreS){
       if(venstreHalv[i] <= hoyreHalv[j]){
        tab[k] = venstreHalv[i];
        i++;
      }else {
        tab[k] = hoyreHalv[j];
        j++;
      }
      k++;
    }
    //resten
    while(i < venstreS){
      tab[k] = venstreHalv[i];
      i++;
      k++;
    }
    while(j < hoyreS){
      tab[k] = hoyreHalv[j];
      j++;
      k++;
    }
  }

}
