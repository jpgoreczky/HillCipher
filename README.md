# Hill Cipher

This project is a Java implementation of the Hill Cipher encryption algorithm. The Hill Cipher is a polygraphic substitution cipher based on linear algebra, which encrypts blocks of plaintext into ciphertext using a matrix key. This implementation was developed as part of the CIS3360 (Securiting in Computing) class at University of Central Florida.

## Overview

The Hill Cipher is a symmetric key cipher, meaning the same key is used for both encryption and decryption. It operates on blocks of plaintext, typically treated as numerical values corresponding to characters in the plaintext alphabet. The key matrix used in the Hill Cipher must be invertible, and its size determines the block size of plaintext processed at a time.

This Java implementation allows users to encrypt plaintext using a provided key matrix. It reads the key matrix and plaintext from separate files and produces ciphertext as output.

## Usage

To use this Hill Cipher implementation, follow these steps:

1. Ensure you have Java installed on your system.
2. Compile the `pa01.java` file using `javac pa01.java`.
3. Run the compiled program using `java pa01 key_matrix_file plaintext_file`.
   - Replace `key_matrix_file` with the file containing the key matrix.
   - Replace `plaintext_file` with the file containing the plaintext to encrypt.

Example usage:
```java
java pa01 key_matrix.txt plaintext.txt
```

## File Formats

- __Key Matrix File__: The key matrix file should contain a number n, indicating the size of the matrix. On the next n lines, the key matrix values should be seperated by spaces or newlines. The matrix should be a square and invertible. For example:
```plaintext
2
2	4
3	5
```

- __Plaintext File__: The plaintext file should contain the plaintext to be encrypted. It can included character's supported by Java's `Scanner` class. For example:
```plaintext
"Not only is the Universe stranger than we think, it is stranger than we can think." ~ Werner Heisenberg
```

## License

This project is licensed under the [MIT License](LICENSE).
