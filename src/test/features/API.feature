# @test
# Feature: User Management

#     Scenario: Verify that I can create a user with a valid access token and valid request body
#     When I create a new user with "valid" access token and "valid" request body
#     Then I assert that the response status code is equal to 201

#   Scenario: Verify that I can not create a user with an invalid access token and valid request body
#     When I create a new user with "invalid" access token and "valid" request body
#     Then I assert that the response status code is equal to 401
#     Then I assert that the response contains "{"message":"Invalid token"}"

#   Scenario: Verify that I can update user with valid access token and valid request body
#     When I create a new user with "valid" access token and "valid" request body
#     Then I assert that the response status code is equal to 201
#     When I update the user with "valid" access token and "valid" request body
#     Then I assert that the response status code is equal to 200

#   Scenario: Verify that I can delete user with valid access token
#     When I create a new user with "valid" access token and "valid" request body
#     Then I assert that the response status code is equal to 201
#     When I delete the user with "valid" access token
#     Then I assert that the response status code is equal to 204

#   Scenario: Verify that I can not create user with invalid request body
#     When I create a new user with "valid" access token and "invalid" request body
#     Then I assert that the response status code is equal to 422
#     Then I assert that the response contains "[{"field":"name","message":"can't be blank"}]"

#   Scenario: Verify that I can not create user with empty  body
#     When I create a new user with "valid" access token and empty body
#     Then I assert that the response status code is equal to 422

#   Scenario: Verify that I can not update the user without a user ID
#     When I create a new user with "valid" access token and "valid" request body
#     Then I assert that the response status code is equal to 201
#     When I update the user with "valid" access token and "valid" request body, without user ID
#     Then I assert that the response status code is equal to 404

#   Scenario: Verify that I can not update deleted user
#     When I create a new user with "valid" access token and "valid" request body
#     Then I assert that the response status code is equal to 201
#     When I delete the user with "valid" access token
#     Then I assert that the response status code is equal to 204
#     When I update the user with "valid" access token and "valid" request body
#     Then I assert that the response status code is equal to 404
#     Then I assert that the response contains "{"message":"Resource not found"}"
