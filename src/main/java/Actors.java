import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import org.json.JSONObject;

public class Actors {
    public static final String API_KEY = "fs3WfKIIowtgJ3sUIQCX/A==ouSZYkG2fPMEx6d8";   // TODO --> add your api key about Actors here
    String netWorth;
    Boolean isAlive;

    public Actors(String netWorth, boolean isAlive){
        //TODO --> (Write a proper constructor using the get_from_api functions)
        this.netWorth = "";
        this.isAlive = false;
    }
    @SuppressWarnings({"deprecation"})
    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name="+
                    name.replace(" ", "+")+"&apikey="+API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int getNetWorthViaApi(String actorsInfoJson){
        //TODO --> (This function must return the "NetWorth")
        actorsInfoJson = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject jsonobject = new JSONObject(actorsInfoJson);
        int result = 0;
        result = jsonobject.getInt("net_worth");
        return result;
    }

    public boolean isAlive(String actorsInfoJson){
        //TODO --> (If your chosen actor is alive it must return true otherwise it must return false)
        actorsInfoJson = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject jsonobject = new JSONObject(actorsInfoJson);
        boolean status = false;
        status = jsonobject.getBoolean("is_alive");
        return status;
    }

    public String getDateOfDeathViaApi(String actorsInfoJson){
        //TODO --> (If your chosen actor is deceased it must return the date of death)  -->
        actorsInfoJson = actorsInfoJson.substring(1, actorsInfoJson.length()-1);
        JSONObject jsonobject = new JSONObject(actorsInfoJson);
        String date = "";
        date = jsonobject.getString("death");
        return date;
    }

}