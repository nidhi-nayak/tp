package recipeio.constants;

public class InputParserConstants {
    public static final int INDEX_COMMAND = 0;
    public static final int FIND_TYPE_INDEX = 0;
    public static final int FIND_ALLERGY_INDEX = 0;
    public static final int RECIPE_NAME_INDEX = 0;
    public static final int INDEX_ID = 1;
    public static final int ARRAY_START_INDEX = 1;
    public static final int FIND_CRITERIA_INDEX = 1;
    public static final int USER_INPUT_INDEX = 1;
    public static final int COOK_TIME_INDEX = 1;
    public static final int CALORIES_INDEX = 2;
    public static final int ALLERGIES_INDEX = 3;
    public static final int MEAL_CATEGORY_INDEX = 4;
    public static final int URL_INDEX = 5;
    public static final int TOTAL_INGREDIENTS_INDEX = 6;

    public static final String MEAL_CAT_GENERAL = "general";
    public static final String MEAL_CAT_DINNER = "dinner";
    public static final String MEAL_CAT_BREAKFAST = "breakfast";
    public static final String MEAL_CAT_LUNCH = "lunch";
    public static final String MEAL_CAT_APPETIZER = "appetizer";
    public static final String MEAL_CAT_DESSERT = "dessert";

    public static final String INTEGER_NEEDED_ERROR_MESSAGE = "Make sure you enter an integer for cook time and" +
            "calories!";
    public static final String INVALID_TASK_FORMAT_ERROR_MESSAGE = "Sorry. I couldn't understand. Please follow the " +
            "correct format.";

    public static final String MEAL_CATEGORY_ERROR_MESSAGE = "Please enter a valid meal category. Here are your" +
            "options:\n BREAKFAST, LUNCH, DINNER, APPETIZER, DESSERT";
    public static final String RECIPE_DELIMETER = ",";
}
