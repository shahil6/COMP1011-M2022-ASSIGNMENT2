package ca.georgiancollege.comp1011m2022assignment2;

import javafx.scene.chart.XYChart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBManager {
    /********************* SINGLETON SECTION *****************************/
    // Step 1. private static instance member variable
    private static DBManager m_instance = null;
    // Step 2. make the default constructor private
    private DBManager() {}
    // Step 3. create a public static entry point / instance method
    public static DBManager Instance()
    {
        // Step 4. Check if the private instance member variable is null
        if(m_instance == null)
        {
            // Step 5. Instantiate a new DBManager instance
            m_instance = new DBManager();
        }
        return m_instance;
    }
    /*********************************************************************/

    // private instance member variables
    private String m_user = "student";
    private String m_password = "123456";
    private String m_connectURL = "jdbc:mysql://localhost:3306/comp1011m2022";

    public XYChart.Series<String, Long> getTable() {
        ArrayList<WPopulation> table = new ArrayList<>();

        String sql = "select Code, Name, Continent, Region, Population from country desc limit 10";

        try
                (
                        Connection connection = DriverManager.getConnection(m_connectURL, m_user, m_password);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            while(resultSet.next())
            {
                // deserialize (decode) the data from database table
                String code = resultSet.getString("Code");
                int population = resultSet.getInt("Population");

                table.add( new WPopulation(code, population));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return drawBarChart(table, "Table");
    }

    public XYChart.Series<String, Long> getCountry() {
        ArrayList<WPopulation> countries = new ArrayList<>();

        String sql = "select Code, Population from country order by Population desc limit 10";

        try
        (
            Connection connection = DriverManager.getConnection(m_connectURL, m_user, m_password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            while(resultSet.next())
            {
                // deserialize (decode) the data from database table
                String code = resultSet.getString("Code");
                int population = resultSet.getInt("Population");

                countries.add( new WPopulation(code, population));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return drawBarChart(countries, "Country");
    }

    public XYChart.Series<String, Long> getContinent() {
        ArrayList<WPopulation> continents = new ArrayList<>();

        String sql = "select Continent, Population from country group by Continent order by Population desc;";

        try
                (
                        Connection connection = DriverManager.getConnection(m_connectURL, m_user, m_password);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            while(resultSet.next())
            {
                // deserialize (decode) the data from database table
                String code = resultSet.getString("Continent");
                int population = resultSet.getInt("Population");

                continents.add( new WPopulation(code, population));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return drawBarChart(continents, "Continent");
    }

    public XYChart.Series<String, Long> getRegion() {
        ArrayList<WPopulation> regions = new ArrayList<>();

        String sql = "select Region, Population from country group by Region order by Population desc;";

        try
                (
                        Connection connection = DriverManager.getConnection(m_connectURL, m_user, m_password);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            while(resultSet.next())
            {
                // deserialize (decode) the data from database table
                String code = resultSet.getString("Region");
                int population = resultSet.getInt("Population");

                regions.add( new WPopulation(code, population));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return drawBarChart(regions, "Region");
    }

    private XYChart.Series<String, Long> drawBarChart(ArrayList<WPopulation> continents, String name) {
        XYChart.Series<String, Long> continentPopulation = new XYChart.Series<>();
        continentPopulation.setName(name);

        for (var continent : continents) {
            var chartData =  new XYChart.Data<>(continent.getCode(), continent.getPopulation());
            continentPopulation.getData().add(chartData);
        }

        return continentPopulation;
    }

    public ArrayList<WPopulation> readWorldTable() {
        ArrayList<WPopulation> worldPopulations = new ArrayList<>();

        String sql = "select Code, Name, Continent, Region, Population from country order by Population desc;";

        try
                (
                        Connection connection = DriverManager.getConnection(m_connectURL, m_user, m_password);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(sql);
                )
        {
            while(resultSet.next())
            {
                // deserialize (decode) the data from database table
                String code = resultSet.getString("Code");
                String name = resultSet.getString("Name");
                String continent = resultSet.getString("Continent");
                String region = resultSet.getString("Region");
                int population = resultSet.getInt("Population");

                worldPopulations.add( new WPopulation(name, continent, region, code, population));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return worldPopulations;
    }
}
