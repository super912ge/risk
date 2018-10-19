package sample.utils;

import sample.model.Continent;
import sample.model.Coordinator;
import sample.model.Country;
import sample.model.GameMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;


/**
 * LoadUtil class manages the reading and writing map information from or to a .map file.
 * It also provides a format checking when reading a .map file to ensure the information can
 * be correctly loaded into the map editor.
 */
public class LoadUtil {
    
    private static String label = "";

    private static HashMap<String,Continent> continentMap = new HashMap<>();

    private static HashMap<String,Country> countryMap = new HashMap<>();

    private static int w = 0;

    private static int h =0 ;

    /**
     * Method to get Continent by the input name.
     * param
     */
    private static Continent getContinent(String name){

       return continentMap.get(name);
    }

    private static List<Continent> getAllContinents(){

        return new ArrayList<>(continentMap.values());
    }

    private static void addContinent(Continent continent){

        continentMap.put(continent.getName(),continent);
    }

    private static List<Country> getAllCountry(){

        return new ArrayList<>( countryMap.values());
    }

    private static Country getCountry(String name){

        return countryMap.get(name);
    }

    private static void addCountry(Country country){

        countryMap.put(country.getName(),country);
    }

    private static void validateContinent(){

    }

    private static void validateCountry(){

        Country country = countryMap.values().stream().filter(i-> i.getCoordinator()==null)
                .findAny().orElse(null);

        if (country!=null) throw new IllegalArgumentException("Illegal adjacent country: "+country.getName());

        countryMap.values().forEach(c->{

            Set<Country> adjacent = c.getAdjacentCountry();

            adjacent.forEach(a-> { if(!a.getAdjacentCountry().contains(c))

                    throw new IllegalArgumentException("Illegal adjacent country "+c.getName()+" and "+ a.getName());
            });
        });
    }

    public static void readFile(File file) throws IOException {

        GameMap map = GameMap.getInstance();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        Stream<String> mapFile = bufferedReader.lines();

        mapFile = mapFile.filter(l->!"".equals(l.trim()));

        mapFile.forEach(s-> {

            s = s.trim();

            if (s.equals("[Map]") || s.equals("[Territories]") || s.equals("[Continents]")) {

                label = s;

            }else {

                switch (label.toLowerCase()) {

                    case "[map]": {

                        String [] split = s.split("=");

                        switch (split[0].trim()){

                            case "author": map.setAuthor(split[1]); break;

                            case "scroll": map.setScroll(split[1]); break;

                            case "image": map.setImage(new File(split[1])); break;

                            case "wrap": map.setWrap(split[1].trim().toLowerCase().equals("yes"));break;

                            case "warn": map.setWarn(split[1].trim().toLowerCase().equals("yes")); break;

                            default: break;
                        }

                        break;

                    }

                    case "[continents]":{

                        String[] str = s.split("=");

                        try {

                            Continent continent = new Continent(str[0], Integer.parseInt(str[1]));

                            LoadUtil.addContinent(continent);


                        }catch (Exception e){

                            e.printStackTrace();
                        }

                        break;
                    }

                    case "[territories]":{

                        String[] str = s.split(",");


                        try {

                            int x = Integer.parseInt(str[1]);

                            int y = Integer.parseInt(str[2]);

                            Country country;

                            if (LoadUtil.getCountry(str[0]) != null) {

                                country = LoadUtil.getCountry(str[0]);

                                country.setCoordinator(new Coordinator(x,y));

                            } else country = new Country(str[0], new Coordinator(x,y));

                            w = Math.max(w,x);

                            h = Math.max(h,y);

                            String continentName = str[3];

                            Continent continent = LoadUtil.getContinent(continentName);

                            if (continent == null) throw new IllegalArgumentException("Continent is invalid for: " + s);

                            continent.getCountries().add(country);

                            country.setContinent(continent);

                            LoadUtil.addContinent(continent);

                            for (int i = 4; i < str.length; i++) {

                                Country adjacent = LoadUtil.getCountry(str[i]);

                                if (adjacent != null) {

                                    country.getAdjacentCountry().add(adjacent);

                                } else {

                                    LoadUtil.addCountry(new Country(str[i]));

                                    country.getAdjacentCountry().add(LoadUtil.getCountry(str[i]));
                                }
                            }

                            LoadUtil.addCountry(country);

                        }catch (Exception e){

                            e.printStackTrace();
                        }

                        break;
                    }

                    default: break;
                }
            }
        });

        LoadUtil.validateContinent();

        LoadUtil.validateCountry();

        map.setContinents(LoadUtil.getAllContinents());

        map.setTerritories(LoadUtil.getAllCountry());

        map.setCoordinator(new Coordinator(w,h));
    }

}
