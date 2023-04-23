package tools;

import core.util.Constants;

import java.util.Arrays;
import java.util.List;

public final class DataProvider {
    public static List<String> getListOfUrls(){
        return Arrays.asList(Constants.URLS);
    }
}
