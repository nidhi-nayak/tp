package recipeio.commands;

import recipeio.InputParser;
import recipeio.recipe.Recipe;
import recipeio.ui.UI;

import java.util.ArrayList;
import java.util.stream.Collectors;
import static recipeio.constants.CommandConstants.RECIPES_INCLUDED;
import static recipeio.constants.CommandConstants.RECIPES_EXCLUDED;

public class FilterByAllergyCommand {

    /**
     * Shows a list of recipes that do not contain the given allergy.
     *
     * @param userInput User's input in the command line.
     * @param recipes The list of current recipes.
     */
    public static void execute(String userInput, ArrayList<Recipe> recipes) {

        String allergy = InputParser.parseAllergyCriteria(userInput);

        ArrayList<Recipe> matches = (ArrayList<Recipe>) recipes.stream()
                .filter(recipe -> !recipe.allergies.contains(allergy))
                .collect(Collectors.toList());

        if (matches.isEmpty()) {
            System.out.println(RECIPES_INCLUDED + allergy);
            return;
        }

        System.out.println(RECIPES_EXCLUDED + allergy);
        UI.printRecipes(matches);
    }
}
