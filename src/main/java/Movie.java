import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONException;
public class Movie {
    public static final String API_KEY = "b094e84b";   // TODO --> add your api key about Movie here
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
        //the constructor
        this.actorsList = new ArrayList<>();
        this.rating = "";
        this.ImdbVotes = 0;

    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) throws IOException {
        try{
            URL url = new URL("https://www.omdbapi.com/?t="+title+"&apikey="+API_KEY);
            URLConnection Url = url.openConnection();
            Url.setRequestProperty("Authorization", "Key" + API_KEY);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine())!=null) {
                stringBuilder.append(line);
            }
            reader.close();
            //handle an error if the chosen movie is not found
            return stringBuilder.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getImdbVotesViaApi(String moviesInfoJson){
        int ImdbVotes = 0;
        JSONObject jsonObject = new JSONObject(moviesInfoJson);
        String imdbVotes = jsonObject.getString("imdbVotes");
        return Integer.parseInt(imdbVotes.replace(",", ""));
        // NOTICE :: you are not permitted to convert this function to return a String instead of an int !!!

    }

    public String getRatingViaApi(String moviesInfoJson){
        //TODO --> (This function must return the rating in the "Ratings" part
        // where the source is "Internet Movie Database")  -->
        String rating = "";
        JSONObject jsonObject = new JSONObject(moviesInfoJson);
        JSONArray ratingsArray = jsonObject.getJSONArray("Ratings");
        for (int i = 0; i < ratingsArray.length(); i++) {
            JSONObject ratingObject = ratingsArray.getJSONObject(i);
            String source = ratingObject.getString("Source");
            if (source.equals("Internet Movie Database")) {
                return ratingObject.getString("Value");
            }
        }
        return "";
    }

    public ArrayList<String> getActorListViaApi(String moviesInfoJson){
        JSONObject jsonObject = new JSONObject(moviesInfoJson);
        String actors = jsonObject.getString("Actors");
        String[] actorArray = actors.split(", ");
        for (String actor : actorArray) {
            actorsList.add(actor);
        }
        return actorsList;
    }
}