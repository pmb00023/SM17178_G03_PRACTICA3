package es.ujaen.git.sm17178_g03_practica1;

/**
 * Created by usuario on 04/10/2017.
 */

public class PersonalData {

    protected String user="";
    protected String pass="";

    public PersonalData(String user, String pass) {
        this.user=user;
        this.pass=pass;

    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}