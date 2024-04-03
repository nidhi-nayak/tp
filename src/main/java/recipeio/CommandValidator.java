package recipeio;

import recipeio.recipe.Recipe;
import recipeio.constants.CommandValidatorConstants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import java.time.format.DateTimeParseException;

import static recipeio.constants.CommandValidatorConstants.INPUT_DETAILS_INDEX;
import static recipeio.constants.CommandValidatorConstants.MAX_RECIPES;
import static recipeio.constants.CommandValidatorConstants.VALID_DETAILS_LENGTH;
import static recipeio.constants.CommandValidatorConstants.VALID_DELETE_LENGTH;
import static recipeio.constants.CommandValidatorConstants.VALID_FILTER_LENGTH;
import static recipeio.constants.CommandValidatorConstants.VALID_FIND_LENGTH;

/**
 * Class containing methods that validate a user's input into the command line.
 */
public class CommandValidator {

    /**
     * Splits a recipe name into individual words.
     *
     * @return ArrayList of words in recipe name.
     */
    public static ArrayList<String> splitName(String recipeName) {
        String[] nameWords = recipeName.split(" ");
        return new ArrayList<>(Arrays.asList(nameWords));
    }


    /**
     * Checks if an input can be parsed as an integer.
     *
     * @param input the String to check.
     * @return status of check.
     */
    public static boolean isParsableAsInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Parameter cannot be parsed as an integer.");
            System.out.println("Please enter an integer from 1 onwards.");
            return false;
        }
    }

    /**
     * Checks if an input can be parsed as a word.
     *
     * @param input the String to check.
     * @return status of check.
     */
    public static boolean isWord(String input) {
        // Regular expression to match only alphabetic characters
        if (!input.matches(CommandValidatorConstants.MATCH_WORD_REGEX)){
            System.out.println("Parameter cannot be parsed as an word.");
            System.out.println("Please enter a word using lower and upper case alphabets.");
            return false;
        }
        return true;
    }

    public static boolean isMealCat(String input) {
        String coreInput = input.trim().toLowerCase();
        boolean isCategory;
        switch (coreInput) {
        case CommandValidatorConstants.MEAL_CAT_GENERAL:
        case CommandValidatorConstants.MEAL_CAT_DINNER:
        case CommandValidatorConstants.MEAL_CAT_LUNCH:
        case CommandValidatorConstants.MEAL_CAT_BREAKFAST:
        case CommandValidatorConstants.MEAL_CAT_APPETIZER:
        case CommandValidatorConstants.MEAL_CAT_DESSERT:
            isCategory = true;
            break;
        default:
            isCategory = false;
        }
        return isCategory;
    }

    /**
     * Checks if an input can be parsed as a word.
     *
     * @param input the String to check.
     * @return status of check.
     */
    public static boolean isParsableAsDate(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Parameter cannot be parsed as a date.");
            System.out.println("Please enter a date in the format yyyy-MM-dd");
            System.out.println("\tInput Example: find date 2024-03-28");
            return false;
        }
    }
    /**
     * Checks if an integer is within the range of number of recipes currently stored.
     *
     * @param recipes the list of current recipes.
     * @param index the index to check.
     * @return status of check.
     */
    public static boolean isWithinRange(ArrayList<Recipe> recipes, int index) {
        if (index > recipes.size() || index < MAX_RECIPES) {
            System.out.println("Sorry, there is no recipe at index: " + index);
            System.out.println("You currently have: " + recipes.size() + " recipes");
            return false;
        }
        return true;
    }

    /**
     * Checks if a detail command is valid.
     * Check fails if number of parameters is not 1, or the parameter is not an integer,
     * or the parameter is out of range.
     *
     * @param userInput User's input in the command line.
     * @param recipes list of current recipes.
     * @return status of check.
     */
    public static boolean isValidDetailCommand(String userInput, ArrayList<Recipe> recipes) {
        String[] details = InputParser.parseDetails(userInput);
        if (details.length != VALID_DETAILS_LENGTH || details[INPUT_DETAILS_INDEX].isEmpty()) {
            System.out.println("The detail function takes in one parameter: {index}");
            System.out.println("\tInput Example: detail 1");
            return false;
        }
        if (!isParsableAsInteger(details[INPUT_DETAILS_INDEX])) {
            System.out.println("\tInput Example: detail 1");
            return false;
        }
        Integer index = InputParser.parseID(userInput);
        return checkIndex(index, recipes);
    }

    /**
     * Checks if a delete command is valid.
     * Check fails if number of parameters is not 1, or the parameter is not an integer,
     * or the parameter is out of range.
     *
     * @param userInput User's input in the command line.
     * @param recipes list of current recipes.
     * @return status of check.
     */
    public static boolean isValidDeleteCommand(String userInput, ArrayList<Recipe> recipes) {
        String[] details = InputParser.parseDetails(userInput);
        if (details.length != VALID_DELETE_LENGTH || details[INPUT_DETAILS_INDEX].isEmpty()) {
            System.out.println("The delete function takes in one parameter: {index}");
            System.out.println("\tInput Example: delete 1");
            return false;
        }
        if (!isParsableAsInteger(details[INPUT_DETAILS_INDEX])) {
            System.out.println("\tInput Example: delete 1");
            return false;
        }
        Integer index = InputParser.parseID(userInput);
        return checkIndex(index, recipes);
    }

    public static boolean checkIndex(Integer index, ArrayList<Recipe> recipes) {
        if (index == null) {
            return false;
        }
        if (!CommandValidator.isWithinRange(recipes, index)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a find command is given two parameters.
     *
     * @param userInput User's input in the command line.
     * @return status of check.
     */
    public static boolean isValidFindCommand(String userInput) {
        String[] details = InputParser.parseDetails(userInput);
        if (details.length != VALID_FIND_LENGTH) {
            System.out.println("The find function accepts two parameters: {type} and {criteria}");
            System.out.println("\tInput Example: find kw pizza");
            System.out.println("\tInput Example: find date 2024-03-28");
            return false;
        }
        return true;
    }

    /**
     * Checks if a filter command is valid.
     * Check fails if number of parameters is not 1, or the parameter is not a word,
     *
     * @param userInput User's input in the command line.
     * @return status of check.
     */
    public static boolean isValidFilterCommand(String userInput) {
        String[] details = InputParser.parseDetails(userInput);
        if (details.length != VALID_FILTER_LENGTH || details[INPUT_DETAILS_INDEX].isEmpty()) {
            System.out.println("The filter function takes in one parameter: {allergy}");
            System.out.println("\tInput Example: filter dairy");
            return false;
        }
        if (!isWord(details[INPUT_DETAILS_INDEX])) {
            System.out.println("\tInput Example: filter dairy");
            return false;
        }
        return true;
    }
}
