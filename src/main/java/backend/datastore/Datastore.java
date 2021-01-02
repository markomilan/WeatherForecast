package backend.datastore;

import java.io.FileWriter;
import java.io.IOException;

public class Datastore {
    String email;
    String city;

    public Datastore(String email, String city) {
        this.email = email;
        this.city = city;

        if(CheckValidity(this.email, this.city)) {
            try {
                FileWriter myWriter = new FileWriter("emails.txt", true);
                myWriter.write(email + " " + city + "\n");
                myWriter.close();
                System.out.println(email + " stored in file");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid email");
        }
    }

    public String getEmail(){
        return email;
    }

    public String getCity(){
        return city;
    }

    public boolean CheckValidity(String email, String city) {
        boolean found = false;
        int i = 0;
        while(i < email.length() && !found) {
            if(email.charAt(i)=='@') {
                found = true;
            }
            i++;
        }
        if (!found) {
            return false;
        }
        found = false;
        while(i < email.length() && !found) {
            if(email.charAt(i)=='.') {
                found = true;
            }
            i++;
        }

        if (city.length() <= 0) {
            return false;
        }

        return found;
    }
}
