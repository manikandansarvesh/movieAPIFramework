package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.PropertyManager;
import utils.TestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static utils.TestUtils.getMapValues;

/**
 * @author manikandan
 */
public class Steps {

    private static final String BASE_URL = "http://api.intigral-ott.net/popcorn-api-rs-7.9.17/v1/promotions";
    private static final String apikey = "webB2BGDMSTGExy0sVDlZMzNDdUyZ";
    private static Response response;
    PropertyManager props = new PropertyManager();


    public void launchEndpointURl() {
        RestAssured.baseURI = BASE_URL;
    }

    public Response validateAPIKey() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.queryParam("apikey", apikey).get();

        System.out.println("Response is : " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);

        return response;
    }

    public void validatePromtion() {

        ResponseBody body = response.getBody();
        String jsonString = body.asString();
        Assert.assertEquals("Response body contains promotions", jsonString.contains("promotions"), true);

        JsonPath jsonPathEvaluator = response.jsonPath();
        String promotionIdValue = jsonPathEvaluator.get("promotions[0].promotionId");
        Assert.assertEquals("Response body contains sabe movie for b2b", promotionIdValue.contains("sabe movie for b2b"), true);
    }

    public void validatePromtionDetails() throws Exception {

        JsonPath jsonPathEvaluator = response.jsonPath();
        String responsePromoType = jsonPathEvaluator.get("promotions[1].promoType");

        Assert.assertEquals(responsePromoType, props.getProps().getProperty("promoType"));
        Assert.assertEquals(jsonPathEvaluator.get("promotions[0].showPrice"), false);
        Assert.assertEquals(jsonPathEvaluator.get("promotions[0].showText"), false);
        int value = jsonPathEvaluator.get("promotions[0].orderId");

        Assert.assertEquals(value, 32);

        /*LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap();
        map.put("ar", new ArrayList<String>());
        String movieName = props.getProps().getProperty("movieName");
        System.out.println(movieName);
        map.get("ar").add(movieName);
        map.get("ar").add(" ");
        map.get("ar").add(" ");

        LinkedHashMap<String, ArrayList<String>> enMAp = new LinkedHashMap();
        enMAp.put("en", new ArrayList<String>());
        enMAp.get("en").add(movieName);
        enMAp.get("en").add(" ");
        enMAp.get("en").add(" ");
        map.putAll(enMAp);*/

        LinkedHashMap<String, ArrayList<String>> responseMap = response.jsonPath().get("promotions[0].localizedTexts");
        Assert.assertEquals(responseMap.size(), getMapValues().size());
        Assert.assertEquals(responseMap, getMapValues());
        String responseProgram = jsonPathEvaluator.get("promotions[0].properties[0].programType ");
        Assert.assertEquals(responseProgram, props.getProps().getProperty("programType"));
        String responseProgram1 = jsonPathEvaluator.get("promotions[4].properties[0].programType ");
        Assert.assertEquals(responseProgram1, props.getProps().getProperty("programType1")); }


    public void validateResponseStatus() {
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    public Response Invalid_APIKey() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.queryParam("apikey", apikey + "Invalid").get();

        System.out.println("Response is : " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 403);

        return response;

    }

    public void InvalidResponseStatus(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }


    public void validateErrorResponseDetails() {

        JsonPath jsonPathEvaluator = response.jsonPath();
        String errorMessage = jsonPathEvaluator.get("error.message");

        Assert.assertEquals("Response body contains invalid api key", errorMessage.equals("invalid api key"), true);
        String errorCode = jsonPathEvaluator.get("error.code");

        Assert.assertEquals(Integer.parseInt(errorCode), 8001);
        Assert.assertNotNull(jsonPathEvaluator.get("error.requestId"));

    }
}


