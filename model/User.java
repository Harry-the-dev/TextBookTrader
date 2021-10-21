package TBEStructure.model;

public class User 
{
    private Integer UserId ;
    private String Name;
    private String Surname;
    private String Email;
    private String PhoneNumber;
    private String Password;


    public User(){};


    public User (Integer userId, String name,
    String surname, String email, String PhoneNumber , String password)
    {
        this.UserId = userId;
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
        this.PhoneNumber = PhoneNumber;
        this.Password = password;
    }

    public void setUserId (Integer userId)
    {
        this.UserId = userId;
    }
    public void setName (String name)
    {
        this.Name = name;
    }
    public void setSurname (String surname)
    {
        this.Surname = surname;
    }
    public void setEmail (String email)
    {
        this.Email = email;
    }
    public void setPhoneNumber (String PhoneNumber)
    {
        this.PhoneNumber = PhoneNumber;
    }
    public void setPassword (String passowrd)
    {
        this.Password = passowrd;
    }


    public Integer getUserId()
    {
       return UserId;
    }
    public String getName()
    {
       return Name;
    }
    public String getSurname()
    {
       return Surname;
    }
    public String getEmail()
    {
       return Email;
    }
    public String getPhoneNumber()
    {
       return PhoneNumber;
    }
    public String getPassword()
    {
       return Password;
    }
}
