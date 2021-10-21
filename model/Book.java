package TBEStructure.model;

public class Book {
    private int BookId;
    private int SellerId;
    private String ISBN;
    private String Title;
    private String Author;
    private int Edition;
    private String Category;
    private String ModuleCode ;
    private int SellerPrice;
    private int SalePrice;

    public Book() {
    }

    public Book(int bookId, int sellerId, String isbn, String title, String author, int edition, String category,String modulecode,
            int sellerprice, int saleprice) {
        this.BookId = bookId;
        this.SellerId = sellerId;
        this.ISBN = isbn;
        this.Title = title;
        this.Author = author;
        this.Edition = edition;
        this.Category = category;
        this.ModuleCode = modulecode;
        this.SellerPrice = sellerprice;
        this.SalePrice = saleprice;

    }

    public int getBookId ()
    {
        return BookId;
    }
    public int getSellerId ()
    {
        return SellerId;
    }
    public String getISBN()
    {
        return ISBN;
    }
    public String getTitle()
    {
        return Title;
    }
    public String getAuthor()
    {
        return Author;
    }
    public int getEdition()
    {
        return Edition;
    }
    public String getCategory()
    {
        return Category;
    }
    public String getModule()
    {
        return ModuleCode;
    }
    public int getSellerPrice()
    {
        return SellerPrice;
    }
    public int getSalePrice()
    {
        return SalePrice;
    }

    public void setBookId (int bookId)
    {
        this.BookId = bookId;
    }
    public void setSellerId( int sellerId )
    {
        this.SellerId = sellerId;
    }
    public void setIsbn (String isbn)
    {
        this.ISBN = isbn;
    }
    public void setTitle (String title)
    {
        this.Title = title;
    }
    public void setAuthor (String author)
    {
        this.Author = author;
    }
    public void setEdition (int edition)
    {
        this.Edition = edition;
    }
    public void setCategory (String category)
    {
        this.Category = category;
    }
    public void setModule (String modulecode)
    {
        this.ModuleCode = modulecode;
    }
    public void setSellerPrice (int sellerprice)
    {
        this.SellerPrice = sellerprice;
    }
    public void setSalePrice (int saleprice)
    {
        this.SalePrice = saleprice;
    }

}
