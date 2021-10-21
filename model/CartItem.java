package TBEStructure.model;



public class CartItem {
    private int CartId;
    private Book book;

    public Integer getCartID() {
        return CartId;
    }

    public void setCartID(Integer cartId) {
        this.CartId = cartId;
    }

    public int getBookID() {
        return this.book.getBookId();
    }

    public String getTitle() {
        return this.book.getTitle();
    }

    public String getModuleCode() {
        return this.book.getModule();
    }

    public int getPrice() {
        return this.book.getSalePrice();
    }

}
