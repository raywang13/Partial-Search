# Partial-Search

This program builds off of my existing  [`Inverted Index`](https://github.com/raywang13/Inverted-Index-Output-to-JSON)
and returns a list of sorted results from the inverted index that starts with a given set of query words.

For example, suppose the inverted index contains the words: `after`, `apple`, `application`, and `happen`. 
If the query word is `app`, the code should return results for both `apple` and `application` but *not* `happen`.

The list is sorted such that the most relevant result is listed first and least relevant result is listed last. Relevance is
determined based on the frequency of the query word and position.

This program also supports search queries, where search results from individual query words are combined together. For example, 
a file only appears once in the search results even if it contains multiple words from a query. 

Queries can be provided in a text file. The input query file will consist of one multi-word search query per line. The query file 
may contain symbols and mixed-case words as the program will perform the necessary transformations to match the queries with the 
inverted index. A sample query file may look like:

```
two
chicka-dee
elephANT & ANTelope
```

There are three different search queries in this example. The first line `two` consists of only one query word. The second line `chicka-dee` 
will have the special character replaced with an empty string to form a one word query `chickadee`. The last line `elephANT & ANTelope` 
will be made all lowercase and have the special characters removed, resulting in the two word query `elephant` and `antelope` instead.

### Result Sorting ###

Search results are ranked using the following criteria:

- **Frequency:** Locations where the query word(s) are more frequent are ranked above others. For example, suppose `a.txt` has 5 partial matches to the query and `b.txt` has 10 partial matches. In that case, `b.txt` will appear in the results before `a.txt`.

- **Position:** For locations that have the same frequency of query word(s), locations where the words appear in earlier positions are ranked above others. For example, suppose `a.txt` and `b.txt` both have the same number of partial matches. If `a.txt` has the first partial match in position 10 and `b.txt` has the first partial match in position 5, then `b.txt` will appear in the results before `a.txt`.

- **Location:** For locations that have the same frequency and position, the results will sorted by path in case-insensitive order. For example, suppose `a.txt` and `b.txt` both have the same number of partial matches and positions. If `a.txt` and `b.txt` are in the same directory, then `a.txt` will appear in the results before `b.txt`.

### Multiple Word Queries ###

For multiple-word queries, the program adds the count for each word together and uses the earliest position any of the query words appear. 

For example, suppose the query words are `apple banana`. Let `file1.txt` have 15 results for `apple` and let `file2.txt` have 5 results for `apple` and 5 results for `banana`. Then, `file1.txt` will be ranked higher than `file2.txt` since the total count of 15 for `file1.txt` is greater than that of 10 for `file2.txt` (even though `file2.txt` has both of the search terms). If `apple` first appears in position 3 in `file2.txt` and `banana` first appears in position 14, then 3 will used as the position for sorting.

### Execution ###

The following are a few examples (non-comprehensive) to illustrate the usage of the command-line arguments. Consider the following example:

```
java Driver -input input/index/simple
            -query input/query/simple.txt
            -index index-simple.json
            -results results-simple.json
```

In this case the program will build an inverted index from all of the text files in the `input/index/simple` directory. It will then build search results for all the queries located in the file `input/query/simple.txt`. The inverted index will be output to the file `index-simple.json` in the current working directory, and the search results will be output to a file `results-simple.json`.
