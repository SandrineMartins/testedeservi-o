import io.restassured.response.Response;
import org.testng.annotations.Test;
import requests.RequestBase;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static requests.postContinentsEndpoint.postContinents;


public class countriesQueryTest {

    String body = "{\"query\":\"\\r\\nquery{\\r\\n  continents(filter:{\\r\\n    code:\\r\\n    {\\r\\n      in:[\\\"AF\\\"]\\r\\n    }\\r\\n    })\\r\\n\\t{\\r\\n    code\\r\\n    }\\r\\n  }\\r\\n\",\"variables\":{}}";
    String bodyError = "{\"query\":\"\\r\\nquery{\\r\\n  (filter:{\\r\\n    code:\\r\\n    {\\r\\n      in:[\\\"AF\\\"]\\r\\n    }\\r\\n    })\\r\\n\\t{\\r\\n    code\\r\\n    }\\r\\n  }\\r\\n\",\"variables\":{}}";

    @Test
    public void shouldReturnStatus200AndRequiredFields(){
        Response response = postContinents(body, Constants.BASE_URL);
        response.
                then().
                assertThat().statusCode(200).body("data.continents[0].code", equalTo("AF"));
    }

    @Test
    public void shouldReturnStatus400(){
        Response response = postContinents(bodyError, Constants.BASE_URL);
        response.
                then().
                assertThat().statusCode(400);
    }

    @Test
    public void shouldReturnTypeOfContinents(){
        Response response = postContinents(body, Constants.BASE_URL);
        response.
                then().
                assertThat().body("data.continents", instanceOf(List.class));
    }
}
