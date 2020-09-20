package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utility {
    static RequestSpecification req;

    public Utility() throws FileNotFoundException {
    }

    public static String getGlobleValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Work\\ApiFramework\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }
    public RequestSpecification requestSpecificationUtility() throws IOException {

        if (req==null) {
            PrintStream reqlog = new PrintStream(new FileOutputStream("reqlogging.txt"));
//            PrintStream resplog = new PrintStream(new FileOutputStream("resplogging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobleValue("URL")).
                    addQueryParam("key", "qaclick123").
                    addFilter(RequestLoggingFilter.logRequestTo(reqlog)).
                    addFilter(ResponseLoggingFilter.logResponseTo(reqlog)).
                    setContentType(ContentType.JSON).build();
            return req;
        }
        return req;
    }
    public String getJsonPathValue(Response response, String key){
        String resp = response.asString();
        JsonPath jp = new JsonPath(resp);
        return jp.getString(key).toString();
    }
}
