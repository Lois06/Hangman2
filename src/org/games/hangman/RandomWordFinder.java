package org.games.hangman;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;


/*
 * @author Lois Mula Fervenza
 * 
 */

/*
 * Hier wird der Random Word ausgesucht im packet org.games.resources in der Txt Datei welche ich selber hinein geschrieben habe
 */

class RandomWordFinder implements Closeable {

    private RandomAccessFile file;

    RandomWordFinder() throws FileNotFoundException, URISyntaxException {
        URL url = this.getClass().getResource("/org/games/resources/words.txt");
        System.out.println(url.getPath());
        File f = new File(url.toURI());
        file = new RandomAccessFile(f, "r");
    }

    String findRandomWord() throws IOException {
        
        movePointerToRandomPosition();

        
        movePointerToBeginningOfWord();

       
        return readWord();
    }

    private String readWord() throws IOException {
        String word = "";
        char key;
        while ((key = (char)file.read()) != '*') {
            word += key;
        }
        return word;
    }

    private void movePointerToBeginningOfWord() throws IOException {
        long pos = file.getFilePointer();
        char ch = (char)file.read();

        while (ch != '*') {
            file.seek(--pos);
            ch = (char)file.read();
        }

        file.seek(pos + 1);
    }

    private void movePointerToRandomPosition() throws IOException {
        long pos;
        char ch;
        do {
            pos = (long) (file.length() * Math.random());
            file.seek(pos);
            ch = (char) file.read();
        } while (ch == '*');
    }

    @Override
    public void close() throws IOException {
        file.close();
    }
}
