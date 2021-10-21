package TBEStructure.model;



public class OrderDetail {
    private int OrderDetailId;
    private int OrderId;
    private int BookId;
    private String Title;
    private String ModuleCode;
    private int Price;
    private Book book;

    public int getOrderDetailId() {
        return OrderDetailId;
    }

    public Integer getOrderID() {
        return OrderId;
    }

    public void setOrderID(int orderID) {
        this.OrderId = orderID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getBookID() {
        return BookId;
    }

    public int setBookID(int bookId) {
        return this.BookId = bookId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getModuleCode() {
        return  ModuleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.ModuleCode = moduleCode;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.book.setSalePrice(price);
    }

}
