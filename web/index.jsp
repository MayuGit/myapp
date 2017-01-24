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

                if (document.getElementById("name") == null || document.getElementById("name").value == "") {
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


        </script>
    </head>
    <body>
        <h1>Welcome to my site.</h1>

        <form action="LoginServlet" onsubmit="return validate()" method="post">
            <table border="0" cellpadding="5">
                <thead>
                    <tr>
                        <td><div id="demo"></div> </td>
                        <td colspan="2">
                            <%
                                String reason = request.getParameter("reason");
                                if (reason == null || reason.equals("")) {
                                    System.out.println("no reson code to diplsay, all ok");
                                } else {
                            %> Invalid user, try again.<%
                                        }
                            %>

                        </td>

                    </tr>

                </thead>
                <tbody>
                    <tr>
                        <td>Enter user id</td>
                        <td colspan="2"><input onkeyup="loadsuggestion(this.value)" type="text" id="name" name="name" size="30"/><br></td>
                    </tr>
                    <tr>
                        <td>Enter password</td>
                        <td colspan="2"><input type="password" id="password" name="password" size="30"/></td>
                    </tr>
                    <tr>
                        <td>Favourite Colour</td>
                        <td colspan="2">
                            <input type="radio" id="colour1" name="colour" value="red"/>Red
                            <input type="radio" id="colour2" name="colour" value="green"/>Green
                            <input type="radio" id="colour3" name="colour" value="blue"/>Blue
               
                            
                        </td>
                    </tr>
                    <tr>  
                        <td></td>
                        <td><button type="submit" name="submit" value="submit">Submit</button></td>
                        <td> <button type="reset" name="Reset" value="Reset"> Reset </button> </td>
                    </tr>
                </tbody>
            </table>
                            New user ? <a href="register.jsp"> click here to register </a>

        </form>
    </body>
</html>
