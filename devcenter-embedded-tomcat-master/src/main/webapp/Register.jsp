<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script>
            function checkInput(){
                var u = document.getElementById("Email");
                var uc = document.getElementById("ConEmail");
                var p = document.getElementById("Password");
                const regex = new RegExp("[a-zA-Z0-9_.+-]+[@][a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");

                if(!(u.value == uc.value)){
                    alert("Your Email Has been typed incorrectly");
                    return false;
                }else if(!(regex.test(u.value))){
                    alert("Your Email is Invalid")
                    return false;
                }else if((p.value).length < 7){
                    alert('Your Password is too Weak\nIt must be 8 or more characters\nNOTE None of these characters are Allowed: ,"');
                    return false;
                }else{
                    return true;
                }
            }

        </script>
    </head>
    <body>
        <form action="RegisterServlet" method="post" onsubmit="return checkInput()">
            <h1>Register</h1>
            <p>Username</p>
            <input type="text" placeholder="Username" name="UserName" id="UserName" required>
            <p>Email</p>
            <input type="text" placeholder="Email" name="Email" id="Email" required>
            <p>Confirm Email</p>
            <input type="text" placeholder="Confirm Email" name="ConEmail" id="ConEmail" required>
            <p>Password</p>
            <input type="password" placeholder="Password" name="Password" id="Password" required>
            <input type="submit" value="Register">
            <a href="index.jsp">Already have Account?</a>
        </form>
        </div>
        </div>
    </body>
</html>
