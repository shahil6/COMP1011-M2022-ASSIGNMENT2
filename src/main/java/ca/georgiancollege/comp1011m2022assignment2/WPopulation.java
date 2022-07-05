package ca.georgiancollege.comp1011m2022assignment2;

public class WPopulation {

    private String name, code, continent, region;
    private long population;

    public WPopulation(String code, long population) {
        this.code = code;
        this.population = population;
    }

    public WPopulation(String name, String continent, String region, String code, long population) {
        this(code, population);
        this.name = name;
        this.continent = continent;
        this.region = region;
    }

    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }
    public long getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "WorldPopulation{" +
                "  name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", population=" + population +
                '}';
    }

}
