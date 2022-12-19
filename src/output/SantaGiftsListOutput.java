package output;

import enums.Category;

public class SantaGiftsListOutput {
    private String productName;
    private Double price;
    private Category category;

    public SantaGiftsListOutput(final String productName, final Double price,
                                final Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public final String getProductName() {
        return productName;
    }

    public final void setProductName(final String productName) {
        this.productName = productName;
    }

    public final Double getPrice() {
        return price;
    }

    public final void setPrice(final Double price) {
        this.price = price;
    }

    public final Category getCategory() {
        return category;
    }

    public final void setCategory(final Category category) {
        this.category = category;
    }
}
