//@@author nidhi-nayak
package recipeio.commands;

import recipeio.recipe.Recipe;

import java.util.ArrayList;

public class AddRecipeCommand {

    /**
     * Adds a recipe to the list.
     *
     * @param recipe The new recipe to be added.
     */
    public static void execute(Recipe recipe, ArrayList<Recipe> recipes) {
        recipes.add(recipe);
    }
}

