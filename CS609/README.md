# CS609 Computational Genomics
Currently, all problems are being presented on ROSALIND, a platform for learning bioinformatics and programming through problem solving.

## hello.py
Introductory "Hello World" type program.

## q1.py
The problem states "After downloading and installing Python, type 'import this' into the Python command line and see what happens. Then,
click the "Download dataset" button below and copy the Zen of Python into the space provided."

## q2.py
The problem states "A string is simply an ordered collection of symbols selected from some alphabet and formed into a word; the length of
a string is the number of symbols that it contains.

An example of a length 21 DNA string (whose alphabet contains the symbols 'A', 'C', 'G', and 'T') is "ATGCTTCAGAAAGGTCTTACG."
* Given: A DNA string ss of length at most 1000 nt.
* Return: Four integers (separated by spaces) counting the respective number of times that the symbols 'A', 'C', 'G', and 'T' occur in ss."

## q3.py
The problem states: 
* Given: A string ss of length at most 200 letters and four integers aa, bb, cc and dd.
* Return: The slice of this string from indices aa through bb and cc through dd (with space in between), inclusively. In other words, we should include elements s[b]s[b] and s[d]s[d] in our slice.

## q4.py
The problem states:
* Given: A file containing at most 1000 lines.
* Return: A file containing all the even-numbered lines from the original file. Assume 1-based numbering of lines.

## q5.py
The problem states:
* Given: A string ss of length at most 10000 letters.
* Return: The number of occurrences of each word in ss, where words are separated by spaces. Words are case-sensitive, and the lines 
in the output can be in any order.

## q6.py
The problem states: 
An RNA string is a string formed from the alphabet containing 'A', 'C', 'G', and 'U'.
Given a DNA string tt corresponding to a coding strand, its transcribed RNA string uu is formed by replacing all occurrences of 'T' in 
tt with 'U' in uu.

* Given: A DNA string tt having length at most 1000 nt.
* Return: The transcribed RNA string of tt.

## q7.py
The problem states:
The 20 commonly occurring amino acids are abbreviated by using 20 letters from the English alphabet (all letters except for B, J, O, 
U, X, and Z). Protein strings are constructed from these 20 symbols. Henceforth, the term genetic string will incorporate protein 
strings along with DNA strings and RNA strings.

The RNA codon table dictates the details regarding the encoding of specific codons into the amino acid alphabet.

* Given: An RNA string ss corresponding to a strand of mRNA (of length at most 10 kbp).
* Return: The protein string encoded by ss.

## q8.py
The problem states:
In DNA strings, symbols 'A' and 'T' are complements of each other, as are 'C' and 'G'. Given a nucleotide p, we denote its complementary nucleotide as p. The reverse complement of a DNA string Pattern = p1…pn is the string Pattern = pn … p1 formed by taking the complement of each nucleotide in Pattern, then reversing the resulting string.

For example, the reverse complement of Pattern = "GTCA" is Pattern = "TGAC".

Reverse Complement Problem:
Find the reverse complement of a DNA string.

* Given: A DNA string Pattern.
* Return: Pattern, the reverse complement of Pattern.

## q9.py
The problem states:
For positive integers aa and nn, aa modulo nn (written amodnamodn in shorthand) is the remainder when aa is divided by nn. For example,
29mod11=729mod11=7 because 29=11×2+729=11×2+7.

Modular arithmetic is the study of addition, subtraction, multiplication, and division with respect to the modulo operation. We say that
aa and bb are congruent modulo nn if amodn=bmodnamodn=bmodn; in this case, we use the notation a≡bmodna≡bmodn.

Two useful facts in modular arithmetic are that if a≡bmodna≡bmodn and c≡dmodnc≡dmodn, then a+c≡b+dmodna+c≡b+dmodn and
a×c≡b×dmodna×c≡b×dmodn. To check your understanding of these rules, you may wish to verify these relationships for a=29a=29, b=73b=73,
c=10c=10, d=32d=32, and n=11n=11.

As you will see in this exercise, some Rosalind problems will ask for a (very large) integer solution modulo a smaller number to avoid
the computational pitfalls that arise with storing such large numbers.

* Given: A protein string of length at most 1000 aa.
* Return: The total number of different RNA strings from which the protein could have been translated, modulo 1,000,000. (Don't neglect the importance of the stop codon in protein translation.)

## q10.py
The problem states:
For DNA strings s1s1 and s2s2 having the same length, their transition/transversion ratio R(s1,s2)R(s1,s2) is the ratio of the total
number of transitions to the total number of transversions, where symbol substitutions are inferred from mismatched corresponding symbols 
as when calculating Hamming distance (see “Counting Point Mutations”).

* Given: Two DNA strings s1s1 and s2s2 of equal length (at most 1 kbp).
* Return: The transition/transversion ratio R(s1,s2)R(s1,s2).

## q11.py
The problem states:
Given a string Text, its k-mer composition Compositionk(Text) is the collection of all k-mer substrings of Text (including repeated 
k-mers). For example,

Composition3(TATGGGGTGC) = {ATG, GGG, GGG, GGT, GTG, TAT, TGC, TGG}

Note that we have listed k-mers in lexicographic order (i.e., how they would appear in a dictionary) rather than in the order of their
appearance in TATGGGGTGC. We have done this because the correct ordering of the reads is unknown when they are generated.

String Composition Problem:
Generate the k-mer composition of a string.

* Given: An integer k and a string Text.
* Return: Compositionk(Text) (the k-mers can be provided in any order).

## q12.py
The problem states:
Find the string spelled by a genome path.

* Given: A sequence of k-mers Pattern1, ... , Patternn such that the last k - 1 symbols of Patterni are equal to the first k - 1 symbols of Patterni+1 for i from 1 to n-1.
* Return: A string Text of length k+n-1 where the i-th k-mer in Text is equal to Patterni for all i.
