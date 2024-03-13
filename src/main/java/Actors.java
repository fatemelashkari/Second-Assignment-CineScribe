import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import org.json.JSONObject;
import java.util.*;

public class Actors {
    public static final String API_KEY = "fs3WfKIIowtgJ3sUIQCX/A==ouSZYkG2fPMEx6d8";   // TODO --> add your api key about Actors here


    public Actors(String netWorth, boolean isAlive){ //constructor
        netWorth = "";
        isAlive = false;
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
        int result = 0;
        actorsInfoJson = actorsInfoJson.substring(1 , actorsInfoJson.length() - 1);
        if(actorsInfoJson.contains("net_worth")){
            JSONObject jsonobject = new JSONObject(actorsInfoJson);
            result = jsonobject.getInt("net_worth");
            return result;
        }
        else{
            System.out.println("Has not found ...");
            return 0;
        }
    }

    public boolean isAlive(String actorsInfoJson){
        //TODO --> (If your chosen actor is alive it must return true otherwise it must return false)
        boolean status = false;
        actorsInfoJson = actorsInfoJson.substring(1 , actorsInfoJson.length() - 1);
        if(actorsInfoJson.contains("is_alive")){
            JSONObject jsonobject = new JSONObject(actorsInfoJson);
            status = jsonobject.getBoolean("is_alive");
            if(status == false){
                System.out.println("Died");
                return status;
            }
            else{
                System.out.println("Alive");
                return status;
            }
        }
        else{
            System.out.println("Has not found ...");
            return false;
        }
    }

    public String getDateOfDeathViaApi(String actorsInfoJson){
        //TODO --> (If your chosen actor is deceased it must return the date of death)  -->
        String date = "";
        actorsInfoJson = actorsInfoJson.substring(1 , actorsInfoJson.length() - 1);
        if(actorsInfoJson.contains("death")){
            JSONObject jsonobject = new JSONObject(actorsInfoJson);
            date = jsonobject.getString("death");
            return date;
        }
        else{
            return "Has not found...";
        }
    }
    //occupation , height , birthday , age

}