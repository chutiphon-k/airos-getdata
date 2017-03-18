import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;

/**
 * Created by chutiphon.k on 3/18/2017 AD.
 */
public class Hello {
    public static void main(String arg[]) throws UnirestException, JSONException {
        HttpResponse<String> response = Unirest.post("http://192.168.1.1/login.cgi")
                .header("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .header("cache-control", "no-cache")
                .header("postman-token", "57d2d33f-9b78-e91e-4062-3e1c071e649c")
                .body("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\nubnt\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"password\"\r\n\r\nubnt\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"uri\"\r\n\r\nstatus.cgi\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--")
                .asString();
        System.out.println("Cookie : " + response.getHeaders().get("Set-Cookie").get(0));

        HttpResponse<String> response2 = Unirest.post("http://192.168.1.1/login.cgi")
                .header("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .header("cache-control", "no-cache")
                .header("cookie", response.getHeaders().get("Set-Cookie").get(0))
                .header("postman-token", "217e2132-1d0f-427b-f024-ea3a91199ad3")
                .body("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\nubnt\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"password\"\r\n\r\nubnt\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"uri\"\r\n\r\nstatus.cgi\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--")
                .asString();

        HttpResponse<JsonNode> response3 = Unirest.get("http://192.168.1.1/status.cgi")
                .header("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                .header("cookie", response.getHeaders().get("Set-Cookie").get(0))
                .header("cache-control", "no-cache")
                .header("postman-token", "d22a7053-e11b-6031-2b4d-3c7675e2a3a9")
                .asJson();

        System.out.println("Http Status : " + response3.getStatus());
        System.out.println("Signal : " + response3.getBody().getObject().getJSONObject("wireless").get("signal"));
    }
}
