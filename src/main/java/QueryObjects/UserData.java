package QueryObjects;

import com.google.gson.annotations.Expose;

public class UserData {
    //@Expose allows us to ignore fields without expose in creating some requests
    @Expose
    public String username = "";
    //TODO: how to get/set password securely
    @Expose
    public String password = "";
    @Expose
    public String email = "";
    @Expose
    public String ipAddress = "127.0.0.1";
    public String token = "";
    public UserData(){};
};