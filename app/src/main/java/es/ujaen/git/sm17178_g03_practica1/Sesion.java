package es.ujaen.git.sm17178_g03_practica1;

/**
 * Created by pablo on 16/12/2017.
 */

public class Sesion{
    public String mSessionId;
    public String mExpires;

    public Sesion(String sessionId, String expires){
        this.mSessionId = sessionId;
        this.mExpires = expires;
    }
    public void setmSessionId(String sessionid){
        this.mSessionId = sessionid ;
    }
    public void setmExpires(String expires){
        this.mExpires = expires ;
    }
    public String getmSessionId(){
        return this.mSessionId;
    }
    public String getmExpires(){
        return this.mExpires;
    }
}
