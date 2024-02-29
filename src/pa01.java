/*============================================================================
| Assignment: pa01 - Encrypting a plaintext file using the Hill cipher
|
| Author: Josie Goreczky
| Language: Java
|
| To Compile: javac pa01.java
|
| To Execute: java -> java pa01 kX.txt pX.txt
| where kX.txt is the keytext file
| and pX.txt is plaintext file
| Note:
| All input files are simple 8 bit ASCII input
| All execute commands above have been tested on Eustis
|
| Class: CIS3360 - Security in Computing - Spring 2024
| Instructor: McAlpin
| Due Date: 03/06/2024
+===========================================================================*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class pa01 {
    public static void main(String[] args) throws Exception {
        // Get file names from command line
        File keyFile = new File(args[0]);
        File plainTextFile = new File(args[1]);

        // Read key file matrix
        int[][] keyMatrix = readMatrix(keyFile);

        // Read plaintext file
        String plainText = readPlainText(plainTextFile);

        // Encrypt plaintext using Hill Cipher
        // String cipherText = encrypt(plainText, keyMatrix);

        // Print out cipher
        // printMatrix(keyMatrix);
        printPlainText(plainText);
        // printCipherText(cipherText);        
    }

    public static int[][] readMatrix(File keyFile) throws FileNotFoundException {
        Scanner key = new Scanner(keyFile);
        int n = key.nextInt();
        int[][] keyMatrix = new int[n][n];
        
        // Read the key file matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                keyMatrix[i][j] = key.nextInt();
            }
        }
        key.close();

        return keyMatrix;
    }

    public static String readPlainText(File plainTextFile) throws FileNotFoundException {
        Scanner plainText = new Scanner(plainTextFile);
        String text = "";

        // Read the plaintext file
        while (plainText.hasNextLine()) {
            text += plainText.next();
        }

        // Remove all non-alphabetic characters and convert to lowercase
        text = text.replaceAll("[^a-zA-Z]", "").toLowerCase();

        plainText.close();

        return text;
    }

    // Encrypt plaintext using Hill Cipher
    public static String encrypt(String plainText, int[][] keyMatrix) {
        char[] cipherText = new char[plainText.length() + 1];

        for (int letter = 0; letter < plainText.length(); letter += keyMatrix.length) {
            // Convert the plaintext to a matrix
            // Multiply the key matrix by the plaintext matrix
            // Convert the resulting matrix back to a string
            for (int r = 0 ; r < keyMatrix.length; r++) {
                for (int c = 0; c < keyMatrix.length; c++) {
                    // System.out.println(keyMatrix[r][c]);
                    if ((letter + c) < plainText.length()) {
                        // System.out.print(plainText.charAt(letter + c));
                        cipherText[letter + r] = (char) (keyMatrix[r][c] * (plainText.charAt(letter + c) - 'a'));
                    }
                    else {
                        // System.out.print(" ");
                        cipherText[letter + r] = (char) (keyMatrix[r][c] * ('x' - 'a'));
                    }
                }
                // Keep text in ascii alphabet
                cipherText[letter + r] = (char) (cipherText[letter + r] % 26 + 'a');
            }

        }

        return cipherText.toString();
    }
    
    public static void printMatrix(int[][] keyMatrix) {
        System.out.println();
        System.out.println("Key Matrix:");
        for (int i = 0; i < keyMatrix.length; i++) {
            for (int j = 0; j < keyMatrix.length; j++) {
                System.out.print(keyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void printPlainText(String plainText) {
        // Print out the plaintext
        System.out.println();
        System.out.println("Plaintext:");
        System.out.println(plainText);
    }

    // public static void printCipherText(String cipherText) {
        
    // }
}

/*=============================================================================
| I Josie Goreczky (5409608) affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/