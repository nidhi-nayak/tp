package recipeio.recipe;

import recipeio.InputParser;
import recipeio.CommandValidator;
import recipeio.commands.AddRecipeCommand;
import recipeio.commands.ListRecipeCommand;
import recipeio.commands.ShowDetailsCommand;
import recipeio.commands.FindCommand;
import recipeio.commands.FilterByAllergyCommand;
import recipeio.commands.DeleteRecipeCommand;
import recipeio.constants.StorageConstants;
import recipeio.storage.Storage;

import recipeio.ui.UI;

import java.util.ArrayList;


import static recipeio.InputParser.parseAdd;
import static recipeio.constants.RecipeListConstants.LIST_COMMAND;
import static recipeio.constants.RecipeListConstants.DETAIL_COMMAND;
import static recipeio.constants.RecipeListConstants.ADD_COMMAND;
import static recipeio.constants.RecipeListConstants.DELETE_COMMAND;
import static recipeio.constants.RecipeListConstants.FIND_COMMAND;
import static recipeio.constants.RecipeListConstants.FILTER_COMMAND;
import static recipeio.constants.RecipeListConstants.HELP_COMMAND;
import static recipeio.constants.RecipeListConstants.NO_RECIPES_ERROR_MESSAGE;


public class RecipeList {
    /**
     * Represents the user's list of recipes (ie their recipe book).
     */
    private final ArrayList<Recipe> recipes;

    /**
     * Accepts recipeData from RecipeIO class, and sets that as initial list of recipe.
     */
    public RecipeList(ArrayList<Recipe> recipeData) {
        this.recipes = recipeData;
    }

    /**
     * Returns the recipe at the specified index.
     *
     * @param index The index of the recipe.
     * @return The recipe at the specified index.
     */
    public Recipe get(int index) {
        return recipes.get(index);
    }

    /**
     * Executes command given from the user.
     *
     * @param command command keyword in the user's command in the command line.
     * @param userInput the full user input in the command line.
     */
    public void executeCommand(String command, String userInput){
        switch (command) {
        case LIST_COMMAND:
            listRecipes();
            break;
        case DETAIL_COMMAND:
            showDetails(userInput);
            break;
        case ADD_COMMAND:
            add(userInput);
            break;
        case DELETE_COMMAND:
            delete(userInput);
            break;
        case FIND_COMMAND:
            find(userInput);
            break;
        case FILTER_COMMAND:
            filter(userInput);
            break;
        case HELP_COMMAND:
            UI.printInstructions();
            break;
        default:
            UI.printInvalidCommandWarning();
            UI.printInstructions();
            break;
        }
    }

    /**
     * Lists the recipes in the recipe book.
     * Calls the execute method in ListRecipeCommand.
     */
    public void listRecipes() {
        ListRecipeCommand.execute(recipes);
    }

    /**
     * Shows the detail of the recipe at given index.
     * Validates the user's command, and exits early if the validation fails.
     * If validation passes, calls the execute method in ShowDetailsCommand.
     *
     * @param userInput input from the user in the command line.
     */
    public void showDetails(String userInput) {
        if (!CommandValidator.isValidDetailCommand(userInput, recipes)){
            return;
        }
        Integer index = InputParser.parseID(userInput);
        if (index == null) {
            return;
        }
        Recipe recipe = get(index-1);
        ShowDetailsCommand.execute(recipe);
    }

    /**
     * Adds a recipe into the list of recipes.
     * Validates the user's command, and exits early if the validation fails.
     * If validation passes, calls the execute method in AddRecipeCommand.
     *
     * @param userInput input from the user in the command line.
     */
    public void add(String userInput) {
        try {
            Recipe newRecipe = parseAdd(userInput);
            AddRecipeCommand.execute(newRecipe, recipes);
            UI.printAddMessage(newRecipe, recipes.size());
            saveRecipes();
        } catch (Exception e){
            UI.printMessage(e.getMessage());
        }
    }

    /**
     * Deletes a recipe from the list of recipes.
     * Validates the user's command, and exits early if the validation fails.
     * If validation passes, calls the execute method in DeleteRecipeCommand.
     *
     * @param userInput input from the user in the command line.
     */
    public void delete(String userInput) {
        if (!CommandValidator.isValidDeleteCommand(userInput, recipes)){
            return;
        }
        Integer index = InputParser.parseID(userInput);
        if (index == null) {
            return;
        }
        DeleteRecipeCommand.execute(index, recipes);
        saveRecipes();
    }

    /**
     * Finds a recipe by keyword or date.
     * Calls the execute method in FindRecipeCommand.
     * Validation is done within FindRecipeCommand.
     *
     * @param userInput input from the user in the command line.
     */
    public void find(String userInput) {
        if (recipes.isEmpty()) {
            System.out.println(NO_RECIPES_ERROR_MESSAGE);
            return;
        }
        FindCommand.execute(userInput, recipes);
    }

    /**
     * Filters a recipe by allergy.
     * Validates the user's command, and exits early if the validation fails.
     * If validation passes, calls the execute method in FilterRecipeCommand.
     *
     * @param userInput input from the user in the command line.
     */
    public void filter(String userInput) {
        if (recipes.isEmpty()) {
            System.out.println(NO_RECIPES_ERROR_MESSAGE);
            return;
        }
        if (!CommandValidator.isValidFilterCommand(userInput)){
            return;
        }
        FilterByAllergyCommand.execute(userInput, recipes);
    }

    /**
     * Saves the recipe book.
     * Calls the saveFile method in Storage.
     * If fails, and error message is shown.
     */
    public void saveRecipes() {
        try {
            Storage.saveFile(recipes);
        } catch (Exception e) {
            System.out.println(StorageConstants.UNSUCCESSFUL_SAVE_MESSAGE);
        }
    }
}
