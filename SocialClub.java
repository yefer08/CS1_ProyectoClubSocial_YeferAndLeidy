import java.util.Scanner;
 
public abstract class SocialClub {
    protected String name;
    protected String id;
 
   
    public abstract void enterNameAndId(Scanner sc);
    public abstract void showUsers();
    public abstract void availableFunds(Scanner sc);
}
