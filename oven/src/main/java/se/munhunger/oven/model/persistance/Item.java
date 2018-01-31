package se.munhunger.oven.model.persistance;

/**
 * @author Marcus Münger
 */
public class Item {
    private String title;
    private boolean purchased;
    private Category category;

    public Item(){
        setPurchased(false);
    }

    public Item(String title) {
        this();
        setTitle(title);
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}