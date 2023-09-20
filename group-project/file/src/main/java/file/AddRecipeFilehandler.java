package file;

import core.Recipe;
import core.RecipeLibrary;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;

public class AddRecipeFilehandler {
    private String filePath;
    private RecipeLibrary library;
    
    /**
     * Contructor that reads the library from the file
     */
    public AddRecipeFilehandler(String file ) {
        this.filePath = Path.of(System.getProperty("user.home")).toString() + file;
        this.library = loadRecipeLibrary();
    }
    /**
     * This method adds the new recipe to the library and 
     * saves the new library to the file with outputstream
     * 
     * To be able to serialize the library all the recices in the library
     * and their belonging profile are set up with an int id before the library
     * saved to the file
     * 
     * @param r Recipe fram the AddRecipeController
     */
    public void SaveRecipe(Recipe r) {
        library.addRecipe(r);

        for (int i = 0; i < library.getSize(); i++) {
            library.getRecipe(i).setId(100 + i);
            library.getRecipe(i).getAuthor().setId(i + 10000000);
        }

        try (final FileOutputStream fout = new FileOutputStream(new File(filePath));
                final ObjectOutputStream out = new ObjectOutputStream(fout)) {
            out.writeObject(library);
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * This nethod reads the RecipeLibrary from the file with inputstream
     * 
     * 
     * @return RecipeLibrary that are read from the file
     */
    public RecipeLibrary loadRecipeLibrary() {
        RecipeLibrary loadedlibrary = new RecipeLibrary();
        try (final FileInputStream fin = new FileInputStream(new File(filePath));
                final ObjectInputStream in = new ObjectInputStream(fin)) {

            loadedlibrary = (RecipeLibrary) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if (new File(filePath).exists()) {
                System.out.println(e.getMessage());
            }
        }
        return loadedlibrary;
    }
}