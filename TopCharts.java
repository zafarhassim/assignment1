import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TopCharts  {
    public static void main(String[] args) throws Exception {
        String homework = "data/homework.txt";
        String note = "data/note.csv";
        String line = "";
        String cvsSplitBy = ",";
        String[] singer;
        final int size = 202;
        String[] original = new String[size];
        String[] arrSinger = new String[size];
        int j = 0;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(note));
        File file = new File(homework);
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        //reads in artists
        while ((line = bufferedReader.readLine()) != null) {
            singer = line.split(cvsSplitBy);
            original[j] = singer[2].toLowerCase().trim();
            arrSinger[j] = singer[2].toLowerCase().trim();
            j++;
        }

        //removes spaces and quotation marks from string
        removeQuotes(arrSinger);
        removeQuotes(original);

        //delete null values
        String[] singer2 = namesAdjust(arrSinger);
        String[] original2 = namesAdjust(original);

        //convert to array (collections), delete duplicates and reverses alphabetical order
        singer2 = Arrays.stream(singer2).distinct().toArray(String[]::new);
        Arrays.sort(singer2, Collections.reverseOrder());

        //record the number of times artist appeared
        int[] appeared = occurred(singer2, original2);

        //create 2D array and print out to text and console
        String[][] fullList = new String[singer2.length][2];
        for (int d = 0; d <= singer2.length - 1; d++) {
            fullList[d][0] = singer2[d];
            fullList[d][1] = Integer.toString(appeared[d]);
        }
        System.out.println("Number of times an artist appeared on charts");
        printWriter.println("Number of times an artist appeared on charts");
        for(int e = 0; e <= fullList.length-1;e++){
            System.out.println("artist: " + fullList[e][0] + " occurred: " + fullList[e][1]);
            printWriter.println("artist: " + fullList[e][0] + " occurred: " + fullList[e][1]);
         }
        System.out.println();
        printWriter.println();

        //implements linked list
        TopStreamingArtists stageName = new TopStreamingArtists();
        for (int g = 0; g <= fullList.length - 1; g++) {
            stageName.insertFirst(fullList[g][0]);
        }

        //displays name of artist in alphabetical order
        stageName.displayList();

        printWriter.close();
        bufferedReader.close();
        fileWriter.close();
    }

    //this method counts the number of times the artist appeared on the charts
       public static int[] occurred (String[]arrSinger, String[]original){
            int[] occurrence = new int[arrSinger.length];
            for (int a = 0; a <= arrSinger.length - 1; a++) {
                int counter = 0;
                for (int b = 0; b <= original.length - 1; b++) {
                    if (arrSinger[a].equals(original[b])) {
                        counter++;
                        occurrence[a] = counter;
                    }
                }
            }
            return occurrence;
        }

        //removes quotes and spaces
     public static void removeQuotes(String[] arrSinger) {
        for (int z = 0; z <= arrSinger.length - 1; z++) {
            if (arrSinger[z].startsWith("\"")) {
                arrSinger[z] = arrSinger[z].substring(1, arrSinger[z].length() - 1).trim();
            }
            if (arrSinger[z].endsWith("\"")) {
                arrSinger[z] = arrSinger[z].substring(0, arrSinger[z].length() - 1).trim();
            }
        }
    }

    //removes null values from array
    public static String [] namesAdjust(String[]arr){
         String[]temp = new String[arr.length-2];
         int ele2 = 2;
         for(int i = 0; i<= arr.length-3;i++){
             temp[i] = arr[ele2];
             ele2++;
         }return temp;
    }
}
