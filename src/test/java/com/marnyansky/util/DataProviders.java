package com.marnyansky.util;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider
    public static Iterator<Object[]> articleSearchFromFileDp1() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/article_search.data")));

        List<Object[]> userData = new ArrayList<>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();

        return userData.iterator();
    }

    //--- bad practice to keep test data inside a method
    @DataProvider
    public static Iterator<Object[]> articleSearchDp1() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"Selenium", "Selenium (software)"});
        data.add(new Object[]{"Selenium (software)", "Selenium (software)"}); //TODO fix: assertion fails
        return data.iterator();
    }

}
