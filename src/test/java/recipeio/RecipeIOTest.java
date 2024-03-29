package recipeio;

import org.junit.jupiter.api.Test;
import recipeio.enums.MealCategory;
import recipeio.recipe.Recipe;
import recipeio.recipe.RecipeList;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


class RecipeIOTest {

    @Test
    public void testAddRecipe() {
        RecipeList recipes = new RecipeList();
        ArrayList<String> allergies = new ArrayList<String>();
        int originalSize = recipes.getSize();
        allergies.add("eggs");
        Recipe newRecipe = new Recipe("cookies", 40, 350, allergies,
                MealCategory.DESSERT, "THIS IS MY URL");
        recipes.add(newRecipe);
        assertTrue(recipes.getSize() == originalSize + 1);
    }

    @Test
    public void testDeleteRecipe() {
        RecipeList recipes = new RecipeList();
        ArrayList<String> allergies = new ArrayList<String>();
        int originalSize = recipes.getSize();
        allergies.add("eggs");
        Recipe newRecipe = new Recipe("cookies", 40, 350, allergies,
                MealCategory.DESSERT, "THIS IS MY URL");
        recipes.add(newRecipe);
        recipes.delete(1);
        assertTrue(recipes.getSize() == originalSize);
    }

    @Test
    public void testStringConversion() {
        Recipe testRecipe = new Recipe("Spaghetti Carbonara", 0, 0, null,
                MealCategory.LUNCH, null);
        assertEquals("Spaghetti Carbonara / 0 min / 0 kcal / url: null", testRecipe.toString());
    }

    @Test
    public void testFindAllergy() {
        ArrayList<String> testAllergies = new ArrayList<>();
        testAllergies.add("eggs");
        Recipe testRecipe = new Recipe("Spaghetti Carbonara", 0, 0, testAllergies,
                MealCategory.LUNCH, null);
        RecipeList testRecipeList = new RecipeList();
        testRecipeList.add(testRecipe);
        testRecipeList.findAllergy("eggs");
        String expectedOutput = "List of recipes with eggs mentioned:\nSpaghetti Carbonara\n";
        assertEquals(testRecipeList.findAllergy("eggs"), expectedOutput);
    }
}
