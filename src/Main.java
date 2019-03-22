import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        // PARSE INPUT FILE
        String[] temp = parser();
        String filename = temp[temp.length-1];
        String[] arr = new String[temp.length-1];
        for (int z = 0; z < temp.length-1; z++){
            arr[z] = temp[z];
        }

        // CREATE BINARY SEARCH TREE
        BinarySearchTree bst = new BinarySearchTree();
        for (int z = 0; z < arr.length; z++){
            bst.addNode(arr[z]);
        }

        // TRAVERSAL
        String[] arrWord = new String[bst.getSize()];
        int[] arrFreq = new int[bst.getSize()];

        int maxLevel = 0;
        while (true) {
            System.out.print("\nEnter the traversal method for " + filename + ":");
            System.out.println("\n1.) In-Order\n2.) Pre-Order\n3.) Post-Order");
            int traverse = scan.nextInt();

            System.out.print("\n--------------------------------------------------------------------");

            if (traverse == 1) {
                System.out.print("\nIN-ORDER output:");
                bst.traverseInOrder(bst.getRoot(), arrWord, arrFreq);
                break;
            }
            else if (traverse == 2) {
                System.out.print("\nPRE-ORDER output:");
                bst.traversePreOrder(bst.getRoot(), arrWord, arrFreq);
                break;
            }
            else if (traverse == 3) {
                System.out.print("\nPOST-ORDER output:");
                bst.traversePostOrder(bst.getRoot(), arrWord, arrFreq, 0, 0);
                break;
            }
            else
                System.out.println("\nInvalid entry");
        }

        for (int z = 0; z < arrWord.length; z++){
            System.out.print(" " + arrWord[z]);
        }

        // TOTAL WORDS
        System.out.println("\n\nTotal number of words in " + filename + ": " + bst.getSize());

        // FREQUENCY
        int uniq = 0;
        for (int z = 0; z < arrFreq.length; z++){
            if (arrFreq[z] == 1)
                uniq++;
        }
        System.out.println("\nNumber of unique words in " + filename + ": " + uniq);

        // MOST FREQUENT WORDS
        int[] iter = new int[arrWord.length];
        int tempFreq = 0;
        int count = 0;
        for (int z = 0; z < bst.getSize(); z++){
            if (arrFreq[z] > tempFreq) {
                iter = new int[arrWord.length];
                count = 0;
                iter[count] = z;
                tempFreq = arrFreq[z];
                count++;
            }
            else if (arrFreq[z] == tempFreq){
                iter[count] = z;
                count++;
            }
        }

        System.out.println("\nThe word(s) that occur(s) most often and the number of times it / they occur(s) is:");
        System.out.println(arrWord[iter[0]] + " = " + arrFreq[iter[0]] + " times(s)");
        for (int z = 1; z < iter.length && iter[z] > 0; z++){
            System.out.println(arrWord[iter[z]] + " = " + arrFreq[iter[z]] + " times(s)");
        }

        // MAX LEVEL
        maxLevel = bst.traversePostOrder(bst.getRoot(), arrWord, arrFreq, 0, 0);
        System.out.println("\nThe maximum height of the tree = " + maxLevel);

        System.out.println("--------------------------------------------------------------------\n");

        // WORD INFO
        String word = scan.nextLine();
        while (true) {
            System.out.print("Enter the word you are looking for in " + filename + " (or enter 'quit' to quit): ");
            word = scan.nextLine();
            if (word.equals("quit"))
                break;

            int tempWord = 0;
            boolean flag = false;
            for (int z = 0; z < arrWord.length; z++) {
                if (word.equals(arrWord[z])) {
                    tempWord = z;
                    flag = true;
                }
            }
            if (!flag)
                System.out.println("Word not found!");
            else
                System.out.println("Found! it appears " + arrFreq[tempWord] + " times in the input text file\n");
        }
    }

    public static String[] parser(){
        Scanner scan = new Scanner(System.in);
        Scanner read = null;
        String filename = null;

        // read input filename
        while (true) {
            System.out.print("Enter the input file name (eg. 'file.txt'): ");
            filename = new String(scan.nextLine());

            try {
                read = new Scanner(new File(filename)).useDelimiter("[ '\\-,!?.\n\"():@#$%^&*;\t\b\r]+");
                break;
            } catch (FileNotFoundException e) {
                System.out.println("File not found\n");
            }
        }

        int count = 0;
        while (read.hasNext()){
            String in = read.next();
            count++;
        }
        read.close();

        if (count == 0){
            System.out.println("File has no words");
            System.exit(0);
        }

        try {
            read = new Scanner(new File(filename)).useDelimiter("[ '\\-,!?.\n\"():@#$%^&;\t\b\r]+");
        } catch (FileNotFoundException e) {
            System.out.println("File not found\n");
            System.exit(0);
        }

        String[] arr = new String[count+1];
        for (int z = 0; z < count; z++){
            arr[z] = read.next().toLowerCase();
        }

        arr[count] = filename;
        return arr;
    }
}