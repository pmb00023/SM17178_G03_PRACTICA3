package es.ujaen.git.sm17178_g03_practica1;

/**
 * Created by usuario on 04/10/2017.
 */

public class ConnectionUserData extends PersonalData {
    protected String connectionIP="127.0.0.1";
    protected short connectionPort=6000;

    public ConnectionUserData(String user, String pass, String connectionIP, short connectionPort) {
        super(user,pass);
        this.connectionIP=connectionIP;
        this.connectionPort=connectionPort;

    }

    public String getConnectionIP() {
        return connectionIP;
    }

    public void setConnectionIP(String connectionIP) {
        this.connectionIP = connectionIP;
    }

    public short getConnectionPort() {
        return connectionPort;
    }

    public void setConnectionPort(short connectionPort) {
        this.connectionPort = connectionPort;
    }
}