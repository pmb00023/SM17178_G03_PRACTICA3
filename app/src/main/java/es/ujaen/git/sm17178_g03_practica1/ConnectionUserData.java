package es.ujaen.git.sm17178_g03_practica1;

/**
 * Created by usuario on 04/10/2017.
 */

public class ConnectionUserData extends PersonalData {
    protected String connectionIP="150.214.170.150";
    protected short connectionPort=80;

    public ConnectionUserData(String user, String pass) {
        super(user,pass);


    }


}