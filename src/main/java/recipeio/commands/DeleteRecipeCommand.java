//@@author nidhi-nayak
package recipeio.commands;

import recipeio.recipe.Recipe;
import recipeio.ui.UI;

import java.util.ArrayList;

public class DeleteRecipeCommand {

    /**
     * Deletes the Recipe from the recipe list.
     * recipeNumber is subtracted by 1 due to 0 based indexing.
     *
     * @param recipeNumber The recipe number from the user.
     * @param recipes The list of current recipes.
     */
    public static void execute(int recipeNumber, ArrayList<Recipe> recipes) {
        Recipe selectedRecipe = recipes.get(recipeNumber - 1);
        recipes.remove(recipeNumber - 1);
        UI.printDeleteMessage(selectedRecipe, recipes.size());
    }
}
