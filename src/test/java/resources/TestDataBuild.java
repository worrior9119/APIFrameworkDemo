package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayload(String name,String lang,String add) {
        AddPlace p = new AddPlace();
        Location loc = new Location();
        p.setAccuracy(20);
        p.setName(name);
        p.setLanguage(lang);
        p.setWebsite("www.ymail.com");
        p.setPhone_number("(+91) 99 13 123 123");
        p.setAddress(add);
        List<String> list = new ArrayList<String>();
        list.add("Park");
        list.add("Australia");
        list.add("123456");
        p.setTypes(list);
        loc.setLat(12.4567879);
        loc.setLng(58.4561234);
        p.setLocation(loc);
        return p;
    }
    public String deletePlacePayload(String placeId){
        String payload = "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}\n";
        return payload;
    }

}
