import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trie {
    private Tree trie = new Tree();

    public void printTrie() {
        trie.print();
    }

    public void addWords(List<String> words) {
        for (String word: words) {
            insert(word);
        }
    }

    public void insert(String word) {
        EntryNode current = trie.getRoot();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);

            EntryNode child = current.getChild(character);
            if (child == null) {
                child = new EntryNode(character, i == word.length() - 1);
                current.addChild(child);
            }

            if (i == word.length() - 1 && child != null) {
                child.setTerminal(true);
            }
            current = child;
        }
    }

    public boolean contains(String potentialWord) {
        EntryNode current = trie.getRoot();
        for (int i = 0; i < potentialWord.length(); i++) {
            char character = potentialWord.charAt(i);

            EntryNode child = current.getChild(character);
            if (child == null) {
                return false;
            }
            current = child;
        }

        return current.isTerminal();
    }

    void remove(String word) {
        EntryNode current = trie.getRoot();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            EntryNode child = current.getChild(character);
            current = child;
        }
        current.setTerminal(false);
        removeHelper(trie.getRoot(), word);
    }

    private boolean removeHelper(EntryNode node, String word) {
        boolean remove = true;

        if (word.length() != 1) {
            remove = removeHelper(node.getChild(word.charAt(0)), word.substring(1));
        }

        EntryNode child = node.getChild(word.charAt(0));

        if(remove && !child.isTerminal() && child.getChildren().size() == 0) {
            node.getChildren().remove(word.charAt((0)));
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("src/main/resources/dictionary.txt"));
        List<String> words = new ArrayList<>();

        while (scan.hasNext()) {
            words.addAll(Arrays.asList(scan.nextLine().split(" ")));
        }

        Trie trie = new Trie();
        trie.addWords(words);
        trie.printTrie();

        System.out.println();
        System.out.println("This test should report false:");
        System.out.println("Contains 's': " + trie.contains("s"));
        System.out.println("Contains 'bye': " + trie.contains("bye"));
        System.out.println("Contains 'bird': " + trie.contains("bird"));

        System.out.println();
        System.out.println("These tests should report true:");
        System.out.println("Contains 'she': " + trie.contains("she"));
        System.out.println("Contains 'sells': " + trie.contains("sells"));
        System.out.println("Contains 'sea': " + trie.contains("sea"));
        System.out.println("Contains 'shells': " + trie.contains("shells"));
        System.out.println("Contains 'by': " + trie.contains("by"));
        System.out.println("Contains 'the': " + trie.contains("the"));
        System.out.println("Contains 'shore': " + trie.contains("shore"));
        System.out.println("Contains 'shorebird': " + trie.contains("shorebird"));
        trie.remove("shorebird");
        trie.remove("sea");
        trie.printTrie();
    }






































































//    public void remove(String word) {
//        if (!this.contains(word)) {
//            return;
//        }
//        EntryNode current = trie.getRoot();
//        EntryNode current2 = trie.getRoot();
//        int wordsBefore = 0;
//        int wordsPassed = 0;
//        for (int i = 0; i < word.length(); i++) {
//            char character = word.charAt(i);
//
//            EntryNode child = current.getChild(character);
//            if (child == null) {
//                throw new NoSuchElementException();
//            }
//            if(current.isTerminal()) {
//                wordsBefore++;
//            }
//            if (i != word.length() - 1) {
//                current = child;
//            }
//        }
//        for (int i = 0; i < word.length(); i++) {
//            char character = word.charAt(i);
//            EntryNode child = current.getChild(character);
//            if (wordsBefore != wordsPassed) {
//                current2 = child;
//            } else {
//                current2.getChild(word.charAt(i)) = null;
//            }
//        }
//    }












}
