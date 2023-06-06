
package Functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import Database.DatabaseConnector;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FunctionsWithSQL {

        private String baseUrl = "https://gorest.co.in/public/v2";
        private String validAccessToken = "69a655b59650c003e5a1621c52f5c4ecc847783814b348e086ecc83e0acea9ec";
        private JsonNode invalidRequestBody;
        private JsonNode validRequestBody;
        private RandomDataGenerator dataGenerator;
        private ObjectMapper objectMapper;

        public FunctionsWithSQL() {
                this.dataGenerator = new RandomDataGenerator();
                this.objectMapper = new ObjectMapper();
                generateRequestBodies();
        }

        public String getAccessToken(String accessTokenType) {
                if (accessTokenType.equals("valid")) {
                        return validAccessToken;
                } else if (accessTokenType.equals("invalid")) {
                        return dataGenerator.generateInvalidAccessToken();

                }
                return null;
        }

        public JsonNode getRequestBody(String requestBodyType) {
                if (requestBodyType.equals("valid")) {
                        return validRequestBody;
                } else if (requestBodyType.equals("invalid")) {
                        return invalidRequestBody;
                }
                return null;
        }

        private void generateRequestBodies() {
                String name = dataGenerator.generateRandomName();
                String email = dataGenerator.generateRandomEmail();
                String gender = dataGenerator.generateRandomGender();
                String status = dataGenerator.generateRandomStatus();

                validRequestBody = objectMapper.createObjectNode()
                                .put("name", name)
                                .put("email", email)
                                .put("gender", gender)
                                .put("status", status);

                invalidRequestBody = objectMapper.createObjectNode()
                                .put("invalidName", name)
                                .put("email", email)
                                .put("gender", gender)
                                .put("status", status);

        }

        public String getUserIdFromDatabase() {
                String userId = null;
                try (Connection connection = DatabaseConnector.getConnection();
                                Statement statement = connection.createStatement();
                                ResultSet resultSet = statement.executeQuery(
                                                "SELECT userId FROM users ORDER BY userId DESC LIMIT 1")) {

                        if (resultSet.next()) {
                                userId = resultSet.getString("userId");
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
                return userId;
        }

        public Response sendPostRequestToCreateUser(String accessTokenType, String requestBodyType) {
                String accessToken = getAccessToken(accessTokenType);
                JsonNode requestBody = getRequestBody(requestBodyType);

                Response response = RestAssured.given()
                                .baseUri(baseUrl)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType("application/json")
                                .body(requestBody)
                                .post("/users");

                int statusCode = response.getStatusCode();
                System.out.println("Response Status Code: " + statusCode);

                String responseBody = response.getBody().asString();
                System.out.println("Response Body: " + responseBody);

                if (statusCode == 201) {
                        Integer userId = response.getBody().jsonPath().getInt("id");
                        String name = requestBody.get("name").asText();
                        String email = requestBody.get("email").asText();
                        String gender = requestBody.get("gender").asText();
                        String status = requestBody.get("status").asText();

                        DatabaseConnector databaseConnector = new DatabaseConnector();
                        databaseConnector.createTable();
                        databaseConnector.insertUserIntoDatabase(userId, name, email, gender, status);
                }

                return response;

        }

        public Response sendPatchRequestToUpdateTheUser(String accessTokenType, String requestBodyType) {
                String accessToken = getAccessToken(accessTokenType);
                JsonNode requestBody = getRequestBody(requestBodyType);

                String userId = getUserIdFromDatabase();

                Response response = RestAssured.given()
                                .baseUri(baseUrl)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType("application/json")
                                .body(requestBody)
                                .patch("/users/" + userId);

                String responseBody = response.getBody().asString();
                System.out.println("Response Body: " + responseBody);

                int statusCode = response.getStatusCode();
                System.out.println("Response Status Code: " + statusCode);

                return response;
        }

        public Response sendDeleteRequestToDeleteTheUser(String accessTokenType) {
                String accessToken = getAccessToken(accessTokenType);

                String userId = getUserIdFromDatabase();

                Response response = RestAssured.given()
                                .baseUri(baseUrl)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType("application/json")
                                .delete("/users/" + userId);

                int statusCode = response.getStatusCode();
                System.out.println("Response Status Code: " + statusCode);

                return response;
        }

        public Response sendPatchRequestToUpdateTheUserWithoutID(String accessTokenType, String requestBodyType) {
                String accessToken = getAccessToken(accessTokenType);
                JsonNode requestBody = getRequestBody(requestBodyType);

                Response response = RestAssured.given()
                                .baseUri(baseUrl)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType("application/json")
                                .body(requestBody)
                                .patch("/users/");

                int statusCode = response.getStatusCode();
                System.out.println("Response Status Code: " + statusCode);

                return response;
        }

        public Response sendPostRequestToCreateUserWithEmptyBody(String accessTokenType) {
                String accessToken = getAccessToken(accessTokenType);
                Response response = RestAssured.given()
                                .baseUri(baseUrl)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType("application/json")
                                .post("/users");

                int statusCode = response.getStatusCode();
                System.out.println("Response Status Code: " + statusCode);
                return response;
        }

}
