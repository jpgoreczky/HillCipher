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

import java.io.*;
import java.util.*;

public class pa01 {
    public static void main(String[] args) throws Exception {
        // Get file names from command line
        
        if (args.length < 2) {
            System.out.println("Usage: java pa01 key_file plaintext_file");
            return;
        }
        
        String  keyFile= args[0];
        String plainTextFile = args[1];
        
        // Read key file matrix
        int[][] keyMatrix = readMatrix(keyFile);

        // Read plaintext file
        String plainText = readPlainText(plainTextFile, keyMatrix.length);

        // Encrypt plaintext using Hill Cipher
        String cipherText = encrypt(plainText, keyMatrix);

        // Print out cipher
        printMatrix(keyMatrix);
        printPlainText(plainText);
        printCipherText(cipherText);     
        
       
    }

    public static int[][] readMatrix(String keyFile) throws FileNotFoundException {
        Scanner key = new Scanner(new File(keyFile));
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

    public static String readPlainText(String plainTextFile, int n) throws FileNotFoundException {
        StringBuilder text = new StringBuilder();
        Scanner textScanner = new Scanner(new File (plainTextFile));

        // Read the plaintext file
        try (BufferedReader br = new BufferedReader(new FileReader(plainTextFile))) {
            String line;
            // Read each line of the file
            while ((line = br.readLine()) != null) {
                // Append each line to the StringBuilder
                text.append(line);
            }
        // If there's an exception, print the stack trace
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Remove all non-alphabetic characters and convert to lowercase
        text = new StringBuilder(text.toString().replaceAll("[^a-zA-Z]", "").toLowerCase());

        // Pad the chunk with 'x' to match the size of the key matrix
        while (text.length() % n != 0) {
            text.append('x');
        }

        textScanner.close();

        return text.toString();
    }

    // Encrypt plaintext using Hill Cipher
    public static String encrypt(String plainText, int[][] keyMatrix) {
        StringBuilder cipherText = new StringBuilder();

        // Traverse the plaintext in blocks of size n (size of key matrix)
        for (int i = 0; i < plainText.length(); i += keyMatrix.length) {
            // Traverse the key matrix
            for (int j = 0; j < keyMatrix.length; j++) {
                int sum = 0;
                // Calculate the sum of the product of the key matrix and the plaintext
                for (int k = 0; k < keyMatrix.length; k++) {
                    sum += (keyMatrix[j][k] * (plainText.charAt(i + k) - 'a'));
                }
                // Append the sum to the cipherText
                cipherText.append((char) ((sum % 26) + 'a'));
            }
        }

        return cipherText.toString();
    }
    
    public static void printMatrix(int[][] keyMatrix) {
        System.out.println();
        System.out.println("Key matrix:");
        for (int i = 0; i < keyMatrix.length; i++) {
            System.out.print("  ");

            for (int j = 0; j < keyMatrix.length; j++) {
                // Format the number with a leading space if it's a single digit
                String formattedNumber = (keyMatrix[i][j] < 10) ? " " + keyMatrix[i][j] : Integer.toString(keyMatrix[i][j]);
                System.out.print(formattedNumber);

                if (j < keyMatrix.length - 1) {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
    
    public static void printPlainText(String plainText) {
        System.out.println();
        System.out.println("Plaintext:");
        
        // Print PlainText 80 letters per row
        for (int i = 0; i < plainText.length(); i++) {
            if (i > 0 && i % 80 == 0) {
                System.out.println();
            }
            System.out.print(plainText.charAt(i));
        }
        System.out.println();   
    }

    public static void printCipherText(String cipherText) {
        System.out.println();
        System.out.println("Ciphertext:");
        
        // Print cipherText 80 letters per row
        for (int i = 0; i < cipherText.length(); i++) {
            if (i > 0 && i % 80 == 0) {
                System.out.println();
            }
            System.out.print(cipherText.charAt(i));
        }
        System.out.println(); 
    }
}

/*=============================================================================
| I Josie Goreczky (5409608) affirm that this program is
| entirely my own work and that I have neither developed my code together with
| any another person, nor copied any code from any other person, nor permitted
| my code to be copied or otherwise used by any other person, nor have I
| copied, modified, or otherwise used programs created by others. I acknowledge
| that any violation of the above terms will be treated as academic dishonesty.
+=============================================================================*/