
//Source: http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
package chatClient;

import java.io.IOException;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class okClient {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;
    public String url = "";
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
        System.out.println("Request body:\n" + body);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public int disconnect(){
        return 0;
    }

    public void setURL(String add){
        url = add;
    }

    public okClient(){
        client = new OkHttpClient();
    }

    public okClient(String add){
        //TODO: verify address
        url = add;
        client = new OkHttpClient();
    }
}
