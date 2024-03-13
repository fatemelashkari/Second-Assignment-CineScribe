import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        runMenu();
    }
    public static void runMenu() throws IOException {
        System.out.println("Hi there :)");
        System.out.println("Choose your desired subject to give you information about :))))");
        System.out.println("1.Movie");
        System.out.println("2.Actor");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        if(choice == 1){
            Movie movie = new Movie(new ArrayList<>() , "",0);
            System.out.print("Enter the name of the movie : ");
            Scanner input = new Scanner(System.in);
            String movieName = input.nextLine();
            System.out.println("");
            String movieData = movie.getMovieData(movieName);
            System.out.print("ImdbVotes : ");
            System.out.println(movie.getImdbVotesViaApi(movieData));
            System.out.print("Rating : ");
            System.out.println(movie.getRatingViaApi(movieData));
            System.out.print("List of the actors : ");
            System.out.println(movie.getActorListViaApi(movieData));
        }
        else if(choice == 2){
            Actors actors = new Actors("",false);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name of the actor : ");
            String actorName = scanner.nextLine();
            String actorData = actors.getActorData(actorName);
            System.out.print("Net Worth : ");
            if(actors.getNetWorthViaApi(actorData) != 0){
                System.out.println(actors.getNetWorthViaApi(actorData));
            }
            else{
                System.out.print("");
            }
            System.out.print("The status of living : ");
            if(actors.isAlive(actorData) == false){
                System.out.print("The date of death : ");
                System.out.println(actors.getDateOfDeathViaApi(actorData));

            }
        }
        else{
            System.out.println("Invalid...");
        }

    }
}