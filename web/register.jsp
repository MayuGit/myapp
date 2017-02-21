<%-- 
    Document   : newjspindex
    Created on : 19-Oct-2016, 18:56:10
    Author     : ubuntu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script type="text/javascript">

            function validate() {

                if (document.getElementById("firstname") == null || document.getElementById("firstname").value == "") {
                    alert("Enter Name");
                    return false;
                }
                if (document.getElementById("password") == null || document.getElementById("password").value == "") {
                    alert("Enter password");
                    return false;
                }

                return true;
            }

            function loadsuggestion(str) {
                
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("demo").innerHTML = this.responseText;
                    }
                };
                xhttp.open("GET", "AjaxTest?q="+str, true);
                xhttp.send();
            }
            
            function test(){
               
               var str = document.getElementById("firstname").value;
               
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        var resp = this.responseText;
                        if(resp.indexOf("true") == -1){
                            document.getElementById("firstname").style.backgroundColor = "green";
                        }
                        else
                           document.getElementById("firstname").style.backgroundColor = "red";  
                    }
                };
                xhttp.open("GET", "UserExists?q="+str, true);
                xhttp.send();
                
            }
            
            


        </script>
    </head>
    <body>
        <h1>Welcome to my site.</h1>
        <%
                                String reason = request.getParameter("reason");
                                if (reason == null || reason.equals("")) {
                                    System.out.println("no reason code to diplsay, all ok");
                                } else {
                            %> User already exists, try again.<%
                                        }
                            %>

        <form action="RegisterServlet" method="post" onsubmit="return validate()">
            
            <table border="0" cellpadding="5">
                <thead>
                    <tr>
                        <td><div id="demo"></div> </td>
                        <td colspan="2">
                            
                        </td>

                    </tr>

                </thead>
                <tbody>
                    <tr>
                        <td>Enter user id</td>
                        <td colspan="2"><input type="text" id="firstname" name="firstname" size="30" onblur="test()"/><button type="button" onclick="test()">check</button><br></td>
                    </tr>
                    <tr>
                        <td>Enter password</td>
                        <td colspan="2"><input type="password" id="password" name="password" size="30"/></td>
                    </tr> 
                    <tr>
                        <td>Enter email</td>
                        <td colspan="2"><input type="text" id="email" name="email" size="30"/></td>
                    </tr> 
                    <tr>
                        <td>Enter last name</td>
                        <td colspan="2"><input type="text" id="lastname" name="lastname" size="30"/></td>
                    </tr>
                    <tr>
                        <td>Enter birthday </td>
                        <td colspan="2"><input type="date" id="bdate" name="bdate" size="30"/></td>
                    </tr> 
                    <tr> 
                    <tr>  
                        <td></td>
                        <td><button type="submit" name="submit" value="submit">Submit</button></td>
                        <td> <button type="reset" name="Reset" value="Reset"> Reset </button> </td>
                    </tr>
                </tbody>
            </table>
            New user ? click here to register

        </form>
    </body>
</html>
