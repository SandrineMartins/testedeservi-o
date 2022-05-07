package requests;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class postContinentsEndpoint extends RequestBase {

        public static Response postContinents(String body, String url){
            Response response =
                    given().
                            header("Content-Type","application/json").
                            and().
                            body(body).
                            when().
                            post(url);
            response.then().log().all();
            return response;
        }
    }