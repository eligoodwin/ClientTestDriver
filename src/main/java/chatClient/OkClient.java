
//Source: http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
package chatClient;

import java.io.IOException;

import QueryObjects.FriendData;
import QueryObjects.IPandPort;
import QueryObjects.UserData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class OkClient {
    public static Gson gson = new Gson();
    //Source: https://stackoverflow.com/questions/4802887/gson-how-to-exclude-specific-fields-from-serialization-without-annotations
    public static Gson exGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;
    public String url = "http://localhost:8080";
    //TODO: change token to correct type
    public int connectToServer(int token){
        return 0;
    }

    //TODO: store response to modularize code and allow flexible handling of response
    public String sendGet() throws IOException {
        if (url == "") return "No URL set!";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String sendPost(String msg) throws IOException{
        if (url == "") return "No URL set!";
        RequestBody body = RequestBody.create(JSON, msg);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String addUser(UserData user) throws IOException {
        if (url == "") return "No URL set!";
        String json = exGson.toJson(user);
        System.out.println("JSON string:\n" + json);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url + "/user")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String checkToken(UserData user) throws IOException{
        if (url == "") return "No URL set!";
        String json = gson.toJson(user);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url + "/testToken")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String testJWT(String jwt) throws IOException{
        if (url == "") return "No URL set!";
        String json = "{\"jwt\":\"" + jwt + "\"}";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url + "/testJWT")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String requestFriend(String username, UserData current) throws IOException{
        if (url == "") return "No URL set!";
        Request request = new Request.Builder()
                .url(url + "/user/" + current.id + "/friend/" + username)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String acceptFriend(String username, UserData current) throws IOException{
        if (url == "") return "No URL set!";
        Request request = new Request.Builder()
                .url(url + "/user/" + current.id + "/friend/" + username + "/2")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //TODO: update return type when determined how to parse code
    public String getFriends(String jwt, UserData current) throws IOException {
        if (url == "") return "No URL set!";
        Request request = new Request.Builder()
                .url(url + "/user/" + current.id + "/friend")
                .build();
        Response response = client.newCall(request).execute();
        String out = response.body().string();
        System.out.println(out);
        return out;
    }

    public String updateIP(UserData current) throws IOException{
        if (url == "") return "No URL set!";
        RequestBody body = RequestBody.create(JSON, "{\"newIP\":\"\"");
        Request request = new Request.Builder()
                .url(url + "/user/" + current.id + "/ipAddress?newIP=" + current.ipAddress + "&portNumber=" + current.peerServerPort)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().string();
    }

    public IPandPort getUser(String user){
        //TODO: make actual connection
        IPandPort data = new IPandPort();
        return data;
    }

    public int disconnect(){
        return 0;
    }

    public void setURL(String add){
        url = add;
    }


    public OkClient(){
        client = new OkHttpClient();
    }

    public OkClient(String add){
        //TODO: verify address
        url = add;
        client = new OkHttpClient();
    }
}
