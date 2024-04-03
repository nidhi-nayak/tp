package recipeio.ui;

import recipeio.recipe.Recipe;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static final String SEPARATOR = "--------------------------------";

    private final Scanner in;

    public UI() {
        this(System.in);
    }

    public UI (InputStream in) {
        this.in = new Scanner(in);
    }

    public static void printLine() {
        System.out.println(SEPARATOR);
    }

    /**
     * Asks user for input in console.
     */
    public String getUserInput() {
        printLine();
        System.out.println("Enter command:");
        String fullInputLine = in.nextLine();
        printLine();
        return fullInputLine;
    }

    /**
     * Greets the user.
     */
    public static void sayHi() {
        printLine();
        System.out.println("Welcome to Recipe.io!");
    }

    public static void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Reports to the user that a recipe has been added successfully.
     * Also reports the number of recipes in the recipe book.
     *
     * @param recipe The recipe that was added.
     * @param recipeListSize The size of the recipe book.
     */
    public static void printAddMessage(Recipe recipe, int recipeListSize) {
        System.out.println("\tWoo hoo chef! I have added this recipe to your recipe book:");
        System.out.print("\t\t");
        System.out.print(recipe.toString());
        System.out.println("\n\tYou now have " + recipeListSize + " recipes in your recipe book. Keep adding some!");
    }

    /**
     * Reports to the user that a recipe has been deleted successfully.
     * Also reports the number of recipes in the recipe book.
     *
     * @param recipe The recipe that was deleted.
     * @param recipeListSize The size of the recipe book.
     */
    public static void printDeleteMessage(Recipe recipe, int recipeListSize) {
        System.out.println("\tOkay chef! I have deleted this recipe from your recipe book:");
        System.out.print("\t\t");
        System.out.print(recipe.toString());
        System.out.println("\n\tYou now have " + recipeListSize + " recipes in your recipe book. Keep adding some!");
    }

    /**
     * Prints the valid meal categories
     */
    public static void printValidMealCategories() {
        System.out.println("\tAccepted meal categories are:");
        System.out.println("\t\tBreakfast");
        System.out.println("\t\tLunch");
        System.out.println("\t\tDinner");
        System.out.println("\t\tDessert");
        System.out.println("\t\tAppetizer");
        System.out.println("\t\tGeneral");
    }

    /**
     * Prints list of accepted instructions.
     */
    public static void printInstructions(){
        System.out.println("\tAccepted commands are:");
        System.out.println("\thelp: shows available commands\n");
        System.out.println("\tlist: shows you list of recipes");
        System.out.println("\t\tInput Example: list\n");
        System.out.println("\tadd NAME,MINUTES,KCALS,SINGULAR TENSE ALLERGIES,CATEGORY,URL: adds a recipe");
        System.out.println("\t\tInput Example: add pizza/34/340/egg/dinner/www.food.com\n");
        System.out.println("\tdelete LIST_NUMBER: deletes a recipe at a given index");
        System.out.println("\t\tInput Example: delete 1\n");
        System.out.println("\tfind kw KEYWORD: finds recipes with a given keyword");
        System.out.println("\t\tInput Example: find kw pizza\n");
        System.out.println("\tfind date YYYY-MM-DD: finds recipes added on a given date");
        System.out.println("\t\tInput Example: find date 2024-03-28\n");
        System.out.println("\tfilter ALLERGY: lists the recipes that do not contain this allergen");
        System.out.println("\t\tInput Example: filter dairy\n");
        System.out.println("\texit: to leave the program");
    }

    /**
     * Prints list of recipes. Refer to toString() method in Recipe class for implementation.
     */
    public static void printRecipes(ArrayList<Recipe> matches) {
        matches.forEach(recipe -> System.out.println("\t\t" + recipe));
    }

    /**
     * Prints warning when an unrecognised command is entered.
     */
    public static void printInvalidCommandWarning() {
        System.out.println("Invalid command.");
    }

    /**
     * Bids farewell to the user.
     */
    public static void bye() {
        System.out.println("See you again chef!");
        printLine();
    }
}
