package lab.pkg2;

import java.util.*;
import java.io.*;

public class Lab2 {

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(new File("spotify.csv")); //reads in data from file
        PrintStream ps = new PrintStream(new File("Artists-WeekOf09062020.txt")); //prints final report to file

        String line = "";

        final int maxArtistPossible = 1000;

        String artists[] = new String[maxArtistPossible];
        int artistsCount[] = new int[maxArtistPossible];

        int currentIndex = 0;
        //reset count to zero
        for (int i = 0; i < artistsCount.length; i++) {
            artistsCount[i] = 0;
        }

        while (sc.hasNextLine()) {

            line = sc.nextLine(); //reads line
            String columns[] = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            //Artist will be at position 2
            String tmpArtist = columns[2];

            //remove quotes from the line
            tmpArtist = tmpArtist.replaceAll("\"", "");
            for (String art : tmpArtist.split(",")) { //checks for duplicate artists 
                boolean found = false;
                for (int i = 0; i < currentIndex; i++) {
                    if (art.equalsIgnoreCase(artists[i])) {
                        artistsCount[i]++;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    artists[currentIndex] = art;
                    artistsCount[currentIndex] = 1;
                    currentIndex++;
                }
            }
        }

        ps.printf("%-25s%s\n", "Artist", "Occurence count"); //prints to output file
        for (int i = 0; i < currentIndex; i++) {
            ps.printf("%-25s%s\n", artists[i], artistsCount[i]);
        }

    }
}
