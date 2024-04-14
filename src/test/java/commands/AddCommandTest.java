package commands;

import org.junit.jupiter.api.Test;
import recipeio.InputParser;
import recipeio.commands.AddRecipeCommand;
import recipeio.enums.MealCategory;
import recipeio.recipe.Recipe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static recipeio.enums.MealCategory.DINNER;

public class AddCommandTest {
    public static final String MULTIPLE_ALLERGY_PARAM_INPUT = "add pizza, 34, 340, dairy/egg, dinner, www.url.com";
    public static final String MISSING_NAME_PARAM_INPUT = "add 34, 340, dairy/egg, dinner, www.url.com";
    public static final String MISSING_TIME_PARAM_INPUT = "add pizza, 340, dairy/egg, dinner, www.url.com";
    public static final String MISSING_KCAL_PARAM_INPUT = "add pizza, 34, dairy/egg, dinner, www.url.com";
    public static final String MISSING_ALLERGY_PARAM_INPUT = "add pizza, 34, 340, dinner, www.url.com";
    public static final String MISSING_MEAL_CAT_PARAM_INPUT = "add pizza, 34, 340, dairy/egg, www.url.com";
    public static final String MISSING_URL_PARAM_INPUT = "add pizza, 34, 340, dairy / egg, dinner";
    ArrayList<Recipe> recipes = new ArrayList<>();

    @Test
    public void testMultipleAllergyParseAdd() {
        recipes.clear();
        Recipe testRecipe = InputParser.parseAdd(MULTIPLE_ALLERGY_PARAM_INPUT);
        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("dairy");
        allergies.add("egg");
        Recipe expectedParsedRecipe = new Recipe(
                "pizza",
                34,
                340,
                allergies,
                DINNER,
                LocalDate.now(),
                "www.url.com");
        AddRecipeCommand.execute(testRecipe, recipes);
        assertEquals(recipes.size(), 1);
        assertEquals(testRecipe.allergies.size(), 2);
        assertEquals(testRecipe.toString(), expectedParsedRecipe.toString());
        assertEquals(testRecipe.name, expectedParsedRecipe.name);
        assertEquals(testRecipe.cookTime, expectedParsedRecipe.cookTime);
        assertEquals(testRecipe.calories, expectedParsedRecipe.calories);
        assertEquals(testRecipe.allergies, expectedParsedRecipe.allergies);
        assertEquals(testRecipe.category, expectedParsedRecipe.category);
        assertEquals(testRecipe.dateAdded, expectedParsedRecipe.dateAdded);
        assertEquals(testRecipe.url, expectedParsedRecipe.url);
    }

    @Test
    public void testAddCommandWithCorrectInput() {
        recipes.clear();
        String input = "add sandwich, 10, 250, nuts, lunch, www.example.com";
        Recipe expectedRecipe = new Recipe("sandwich", 10, 250, new ArrayList<>(Arrays.asList("nuts")), MealCategory.LUNCH, LocalDate.now(), "www.example.com");
        Recipe actualRecipe = InputParser.parseAdd(input);
        AddRecipeCommand.execute(actualRecipe, recipes);
        assertEquals(1, recipes.size());
        assertEquals(expectedRecipe.toString(), actualRecipe.toString());
    }

    @Test
    public void testAddCommandWithExtraSpaces() {
        recipes.clear();
        String input = "add sandwich,  10, 250,  nuts, lunch,  www.example.com ";
        Recipe actualRecipe = InputParser.parseAdd(input);
        Recipe expectedRecipe = new Recipe("sandwich", 10, 250, new ArrayList<>(Arrays.asList("nuts")), MealCategory.LUNCH, LocalDate.now(), "www.example.com");
        AddRecipeCommand.execute(actualRecipe, recipes);
        assertEquals(1, recipes.size());
        assertEquals(expectedRecipe.toString(), actualRecipe.toString());
    }

    @Test
    public void testAddCommandWithIncorrectTypes() {
        recipes.clear();
        String input = "add sandwich, ten, two fifty, nuts, lunch, www.example.com";
        assertThrows(NumberFormatException.class, () -> InputParser.parseAdd(input));
    }

    @Test
    public void testAddCommandWithBoundaryValues() {
        recipes.clear();
        String input = "add massive sandwich, 0, 10000, nuts, dinner, www.bigrecipe.com";
        Recipe expectedRecipe = new Recipe("massive sandwich", 0, 10000, new ArrayList<>(Arrays.asList("nuts")), MealCategory.DINNER, LocalDate.now(), "www.bigrecipe.com");
        Recipe actualRecipe = InputParser.parseAdd(input);
        AddRecipeCommand.execute(actualRecipe, recipes);
        assertEquals(1, recipes.size());
        assertEquals(expectedRecipe.toString(), actualRecipe.toString());
    }

    @Test
    public void testAddCommandWithInvalidMealCategory() {
        recipes.clear();
        String input = "add sushi, 15, 300, fish, anyday, www.sushilover.com";
        assertThrows(IllegalArgumentException.class, () -> InputParser.parseAdd(input));
    }

    @Test
    public void testAddCommandWithAllCaps() {
        recipes.clear();
        String input = "add PIZZA, 30, 300, CHEESE, DINNER, www.pizzaplace.com";
        Recipe expectedRecipe = new Recipe("PIZZA", 30, 300, new ArrayList<>
                (Arrays.asList("CHEESE")), MealCategory.DINNER, LocalDate.now(), "www.pizzaplace.com");
        Recipe actualRecipe = InputParser.parseAdd(input);
        AddRecipeCommand.execute(actualRecipe, recipes);
        assertEquals(1, recipes.size());
        assertEquals(expectedRecipe.toString(), actualRecipe.toString());
    }

    @Test
    public void testMissingNameParam() {
        recipes.clear();
        String input = MISSING_NAME_PARAM_INPUT;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parseAdd(input));
        System.out.println(exception.getMessage());
    }

    @Test
    public void testMissingTimeParam() {
        recipes.clear();
        String input = MISSING_TIME_PARAM_INPUT;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parseAdd(input));
    }

    @Test
    public void testMissingKcalParam() {
        recipes.clear();
        String input = MISSING_KCAL_PARAM_INPUT;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parseAdd(input));
    }

    @Test
    public void testMissingAllergyParam() {
        recipes.clear();
        String input = MISSING_ALLERGY_PARAM_INPUT;
        Recipe expectedRecipe = new Recipe(
                "pizza",
                34,
                340,
                new ArrayList<>(),  // Assuming missing allergies result in an empty list
                MealCategory.DINNER,
                LocalDate.now(),
                "www.url.com"
        );
        Recipe actualRecipe = InputParser.parseAdd(input);
        AddRecipeCommand.execute(actualRecipe, recipes);
        assertEquals(1, recipes.size());
        assertEquals(expectedRecipe.toString(), actualRecipe.toString());
    }

    @Test
    public void testMissingMealCategoryParam() {
        recipes.clear();
        String input = MISSING_MEAL_CAT_PARAM_INPUT;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parseAdd(input));
    }

    @Test
    public void testMissingUrlParam() {
        recipes.clear();
        String input = MISSING_URL_PARAM_INPUT;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> InputParser.parseAdd(input));
    }
}
