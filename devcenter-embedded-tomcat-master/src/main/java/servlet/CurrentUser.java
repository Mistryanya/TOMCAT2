package servlet;

public class CurrentUser {

    private String Name;
    private String Email;
    private String Password;
    private final int Id;

    public CurrentUser(int id,String name, String email, String password){
        this.Id = id;
        this.Name = name;
        this.Email = email;
        this.Password = password;

    }



    public String getName(){
        return Name;
    }
    public String getEmail(){
        return Email;
    }
    public String getPassword(){
        return Password;
    }
    public int getId(){
        return Id;
    }

    public void setName(String NewName){
        Name = NewName;
    }
    public void setEmail(String NewEmail){
        Email = NewEmail;
    }
    public void setPassword(String NewPassword){Password = NewPassword;
    }



}
