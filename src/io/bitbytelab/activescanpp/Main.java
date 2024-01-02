package io.bitbytelab.activescanpp;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try {
            URI uri = new URI("https://example.com/getName?name=1");
            System.out.println("Host: " + uri.getHost());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}