package model1;
import java.util.regex.Pattern;


public class Customer {
    private String firstName;
    private String lastName;
    private final String email;

    public Customer(String firstName,String lastName,String email){
    String checkEmail="^(.+)@(.+)\\.(.+)$";
    Pattern pattern =Pattern.compile(checkEmail);
    if(!pattern.matcher(email).matches()){
        throw new IllegalArgumentException("Error : Invalid emial format!! please use 'user@example.com' format only");
    }
    this.firstName=firstName;
    this.lastName=lastName;
    this.email=email;
}

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public String toString(){
        return "First Name : "+firstName+
        "\n     Last Name : "+lastName+
        "\n     Email : "+email+
        "\n }";
    }
}

