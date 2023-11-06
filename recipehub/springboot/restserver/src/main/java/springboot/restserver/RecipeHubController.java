package springboot.restserver;

import core.Profile;
import core.Recipe;
import core.RecipeLibrary;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ui.DirectRecipeHubAccess;
import ui.RecipeHubAccess;

/**
 * The service implementation.
 */
@RestController
@RequestMapping(RecipeHubController.RECIPEHUB_MODEL_SERVICE_PATH)
public class RecipeHubController {

    public static final String RECIPEHUB_MODEL_SERVICE_PATH = "recipehub";

    private RecipeHubAccess access = new DirectRecipeHubAccess();

    /**
     * Get the recipe library.
     * @return the recipe library
     */
    @GetMapping(path = "/recipelibrary")
    public RecipeLibrary getRecipeLibrary() {
        return access.getRecipeLibrary();
    }

    /**
     * Saves a recipe.
     * @param recipe - the recipe to add
     * @return true if the recipe was added, false otherwise
     */
    @PutMapping(path = "/recipelibrary")
    public boolean putRecipe(@RequestBody Recipe recipe) {
        return access.saveRecipe(recipe);
    }

    /**
     * Removes a recipe.
     * @param recipe - the recipe to remove
     * @return true if the recipe was removed, false otherwise
     */
    @DeleteMapping(path = "/recipelibrary")
    public boolean removeRecipe(@RequestBody Recipe recipe) {
        return access.removeRecipe(recipe);

    }

    /**
     * Get all profiles.
     * @return the profiles
     */
    @GetMapping(path = "/profiles")
    public List<Profile> getProfiles() {
        System.out.println("getProfiles()");
        return access.getProfiles();
    }

    /**
     * Adds a profile.
     * @param profile - the profile to add
     * @return true if the profile was added, false otherwise
     */
    @PutMapping(path = "/profiles")
    public boolean putProfile(@RequestBody Profile profile) {
        System.out.println("putProfile(Profile profile) :" + profile.getUsername());
        return access.saveProfile(profile);
    }
}