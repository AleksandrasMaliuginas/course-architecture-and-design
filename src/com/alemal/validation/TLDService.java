package com.alemal.validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;

public class TLDService {
    private HashSet<String> listOfTLD = new HashSet<>();

    public TLDService() {
        getTLDList();
    }

    public boolean validateTLD(String tld) {
        return listOfTLD.contains(normalizeTLD(tld));
    }

    private void getTLDList() {
        HttpURLConnection connection = null;
        try {
            connection = getConnection();
            fetchData(connection);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private HttpURLConnection getConnection() throws IOException {
        var url = new URL("https://data.iana.org/TLD/tlds-alpha-by-domain.txt");
        var con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        return con;
    }

    private void fetchData(HttpURLConnection con) throws IOException {
        String inputLine;
        try (var in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))
        ) {
            var versionHeader = in.readLine();

            while ((inputLine = in.readLine()) != null) {
                listOfTLD.add(normalizeTLD(inputLine));
            }
        }
    }

    private String normalizeTLD(String tld) {
        return tld.toUpperCase();
    }
}
