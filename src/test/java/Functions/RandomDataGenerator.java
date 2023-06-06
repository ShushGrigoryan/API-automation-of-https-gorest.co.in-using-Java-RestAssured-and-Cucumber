package Functions;

import com.github.javafaker.Faker;

public class RandomDataGenerator {
    private Faker faker;

    public RandomDataGenerator() {
        faker = new Faker();
    }

    public String generateInvalidAccessToken(){
        return faker.crypto().md5();
    }

    public String generateRandomName() {
        return faker.name().fullName();
    }

    public String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public String generateRandomGender() {
        String[] genders = { "Male", "Female" };
        int randomIndex = faker.random().nextInt(genders.length);
        return genders[randomIndex];
    }

    public String generateRandomStatus() {
        String[] statuses = { "Active", "Inactive" };
        int randomIndex = faker.random().nextInt(statuses.length);
        return statuses[randomIndex];
    }

  

}
