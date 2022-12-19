package input;

import enums.Category;

public class SantaGiftsListInput {
    private String productName;
    private Double price;
    private Category category;
    private Integer quantity;

    public final Integer getQuantity() {
        return quantity;
    }

    public final void setQuantity(final Integer quantity) {
        this.quantity = quantity;
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
