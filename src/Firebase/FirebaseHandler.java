package Firebase;

import com.mashape.unirest.http.Unirest;

public class FirebaseHandler {
    public FirebaseHandler()
    {
        printString();
    }

    public void printString()
    {
        try {
            String thirteen = Unirest.get("https://web-quickstart-ea367.firebaseio.com/0/FORMID.json").asString().getBody();
            System.out.println(thirteen);
        }
        catch(Exception e)
        {
            System.out.println("No go");
        }
    }
}
