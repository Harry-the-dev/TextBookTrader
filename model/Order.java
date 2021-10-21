package TBEStructure.model;

import java.util.Date;

public class Order {

    private int OrderId;
    private int UserId;
    private int ShippingId;
    private int PaymentId;
    private Date DateTime;
    private Double TotalAmount;
    private Boolean Orderfulfilled;
    public Order (){}

    public Order (int orderId , int userId, int shippingId , int paymentId ,Date dateTime , Double totalAmount,Boolean orderfullfilled ) 
    {
        this.OrderId = orderId;
        this.UserId = userId;
        this.ShippingId = shippingId;
        this.PaymentId = paymentId;
        this.DateTime = dateTime;
        this.TotalAmount = totalAmount;
        this.Orderfulfilled = orderfullfilled;
    }

    public int getOrderId ()
    {
      return OrderId;
    }
    public int getUserId ()
    {
      return UserId;
    }
    public int getShippingId ()
    {
        return ShippingId;
    }
    public int getPaymentId ()
    {
        return PaymentId;
    }
    public Date getDateTime ()
    {
        return DateTime;
    }
    public Double getTotalAmount ()
    {
        return TotalAmount;
    }
    public Boolean getOrderFulfilled  ()
    {
        return Orderfulfilled;
    }

    public void setOderid ( int orderId)
    {
      this.OrderId = orderId;
    }
    public void setUserId ( int userId)
    {
      this.UserId = userId;
    }
    public void setShippingId ( int shippingId)
    {
      this.ShippingId = shippingId;
    }
    public void setPaymentId ( int paymentId)
    {
      this.PaymentId = paymentId;
    }
    public void setDateTime ( Date dateTime )
    {
        this.DateTime = dateTime;
    }
    public void setTotalAmount ( Double totalAmount)
    {
        this.TotalAmount = totalAmount;
    }
    public void setOrderfullfilled ( Boolean orderFulFilled)
    {
        this.Orderfulfilled = orderFulFilled;
    }
   
}
