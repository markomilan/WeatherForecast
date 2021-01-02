package backend.sending;

import org.apache.commons.mail.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Sending {

    public HashMap<String, String> datas = new HashMap<>();
    public ArrayList<String[]> recommendationList = new ArrayList<String[]>();
    public String[] recommendation = new String[4];

    public Sending(){
        readEmails();
        readRecomm();

        for (Map.Entry<String,String> entry : datas.entrySet()) {
            if(entry.getKey().length() > 0 && entry.getValue().length() > 0) {
                getRecommendation(entry.getValue());
                sending(entry.getKey());
            }
        }
        System.out.println("Sending success");
    }

    public void getRecommendation(String city) {
        Recommendations recommendations = new Recommendations(city);

        Random r = new Random();
        int low = 0;
        int high = 2;
        int result = r.nextInt(high-low) + low;

        for (int i = 0; i < recommendationList.size(); ++i) {

            if (parseInt(recommendationList.get(i)[0]) <= parseInt(recommendations.getDayTemp())
                    && parseInt(recommendations.getRain()) == 0) {

                recommendation[0] = recommendations.getDayTemp();
                recommendation[1] = recommendations.getRain();
                recommendation[2] = recommendationList.get(i + result)[2];
                recommendation[3] = recommendationList.get(i + result)[3];
                break;

            }
            else if (parseInt(recommendationList.get(i)[0]) <= parseInt(recommendations.getDayTemp())
                    && parseInt(recommendations.getRain()) != 0) {

                i = i + 3;
                recommendation[0] = recommendations.getDayTemp();
                recommendation[1] = recommendations.getRain();
                recommendation[2] = recommendationList.get(i + result)[2];
                recommendation[3] = recommendationList.get(i + result)[3];
                break;
            }
        }
    }

    public void readEmails(){
        try {
            File myObj = new File("emails.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataList = data.split(" ");
                datas.put(dataList[0], dataList[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred (Emails) .");
            e.printStackTrace();
        }
    }

    public void readRecomm(){
        try {
            File myObj = new File("recomm.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataList = data.split(",");
                if (dataList.length > 1) {
                    recommendationList.add(dataList);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred (Recomm) .");
            e.printStackTrace();
        }
    }

    public void sending(String address)
    {
        MultiPartEmail email=new MultiPartEmail();
        //Host
        email.setHostName("smtp.googlemail.com");
        //Username and password
        email.setAuthenticator(new DefaultAuthenticator("program4caster","Program4.Caster"));
        //Set email host SSL to true
        email.setSSL(true);
        try {
            //Email from
            email.setFrom("program4caster@gmail.com");
        } catch (EmailException e) {
            e.printStackTrace();
        }
        //Subject
        email.setSubject("Program4caster daily suggestion");
        try {
            //Message
            //email.setMsg("This is a test from Java Email");
            email.setMsg(recommendation[0] + "° and " + recommendation[1] + "mm rain, Activite: " + recommendation[2] + " , Clothes: "  + recommendation[3] );
        } catch (EmailException e) {
            e.printStackTrace();
        }
        try {
            //Email To
            email.addTo(address);
        } catch (EmailException e) {
            e.printStackTrace();
        }
        try {
            //Sending
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }
}
