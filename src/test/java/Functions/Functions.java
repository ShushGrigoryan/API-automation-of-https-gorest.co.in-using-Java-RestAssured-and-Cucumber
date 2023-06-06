
// package Functions;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import io.restassured.RestAssured;
// import io.restassured.response.Response;

// public class Functions {

//         private String baseUrl = "https://gorest.co.in/public/v2";
//         private String validAccessToken = "69a655b59650c003e5a1621c52f5c4ecc847783814b348e086ecc83e0acea9ec";
//         private String userId;
//         private JsonNode invalidRequestBody;
//         private JsonNode validRequestBody;
//         private RandomDataGenerator dataGenerator;
//         private ObjectMapper objectMapper;

//         public Functions() {
//                 this.dataGenerator = new RandomDataGenerator();
//                 this.objectMapper = new ObjectMapper();
//                  generateRequestBodies();
//         }

//         private void generateRequestBodies() {
//                 String name = dataGenerator.generateRandomName();
//                 String email = dataGenerator.generateRandomEmail();
//                 String gender = dataGenerator.generateRandomGender();
//                 String status = dataGenerator.generateRandomStatus();

//                 validRequestBody = objectMapper.createObjectNode()
//                                 .put("name", name)
//                                 .put("email", email)
//                                 .put("gender", gender)
//                                 .put("status", status);

//                 invalidRequestBody = objectMapper.createObjectNode()
//                                 .put("invalidName", name)
//                                 .put("email", email)
//                                 .put("gender", gender)
//                                 .put("status", status);
//         }

//         public String getAccessToken(String accessTokenType) {
//                 if (accessTokenType.equals("valid")) {
//                         return validAccessToken;
//                 } else if (accessTokenType.equals("invalid")) {
//                         return dataGenerator.generateInvalidAccessToken();

//                 }
//                 return null;
//         }

//         public JsonNode getRequestBody(String requestBodyType) {
//                 if (requestBodyType.equals("valid")) {
//                         return validRequestBody;
//                 } else if (requestBodyType.equals("invalid")) {
//                         return invalidRequestBody;
//                 }
//                 return null;
//         }

//         public Response sendPostRequestToCreateUser(String accessTokenType, String requestBodyType) {
//                 String accessToken = getAccessToken(accessTokenType);
//                 JsonNode requestBody = getRequestBody(requestBodyType);

//                 Response response = RestAssured.given()
//                                 .baseUri(baseUrl)
//                                 .header("Authorization", "Bearer " + accessToken)
//                                 .contentType("application/json")
//                                 .body(requestBody)
//                                 .post("/users");

//                 userId = response.getBody().jsonPath().getString("id");
//                 int statusCode = response.getStatusCode();
//                 System.out.println("Response Status Code: " + statusCode);

//                 String responseBody = response.getBody().asString();
//                 System.out.println("Response Body: " + responseBody);
//                 return response;
//         }

//         public Response sendPatchRequestToUpdateTheUser(String accessTokenType, String requestBodyType) {
//                 String accessToken = getAccessToken(accessTokenType);
//                 JsonNode requestBody = getRequestBody(requestBodyType);
//                 Response response = RestAssured.given()
//                                 .baseUri(baseUrl)
//                                 .header("Authorization", "Bearer " + accessToken)
//                                 .contentType("application/json")
//                                 .body(requestBody)
//                                 .patch("/users/" + userId);

//                 String responseBody = response.getBody().asString();
//                 System.out.println("Response Body: " + responseBody);

//                 int statusCode = response.getStatusCode();
//                 System.out.println("Response Status Code: " + statusCode);

//                 return response;
//         }

//         public Response sendDeleteRequestToDeleteTheUser(String accessTokenType) {
//                 String accessToken = getAccessToken(accessTokenType);
//                 Response response = RestAssured.given()
//                                 .baseUri(baseUrl)
//                                 .header("Authorization", "Bearer " + accessToken)
//                                 .contentType("application/json")
//                                 .delete("/users/" + userId);

//                 int statusCode = response.getStatusCode();
//                 System.out.println("Response Status Code: " + statusCode);

//                 return response;
//         }

//         public Response sendPatchRequestToUpdateTheUserWithoutID(String accessTokenType, String requestBodyType) {
//                 String accessToken = getAccessToken(accessTokenType);
//                 JsonNode requestBody = getRequestBody(requestBodyType);

//                 Response response = RestAssured.given()
//                                 .baseUri(baseUrl)
//                                 .header("Authorization", "Bearer " + accessToken)
//                                 .contentType("application/json")
//                                 .body(requestBody)
//                                 .patch("/users/");

//                 int statusCode = response.getStatusCode();
//                 System.out.println("Response Status Code: " + statusCode);

//                 return response;
//         }

//         public Response sendPostRequestToCreateUserWithEmptyBody(String accessTokenType) {
//                 String accessToken = getAccessToken(accessTokenType);
//                 Response response = RestAssured.given()
//                                 .baseUri(baseUrl)
//                                 .header("Authorization", "Bearer " + accessToken)
//                                 .contentType("application/json")
//                                 .post("/users");

//                 userId = response.getBody().jsonPath().getString("id");
//                 int statusCode = response.getStatusCode();
//                 System.out.println("Response Status Code: " + statusCode);
//                 return response;
//         }

// }
