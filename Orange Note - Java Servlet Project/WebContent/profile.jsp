	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	    <%@ page import="java.util.*" %>
	
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>&#x1F34A; Orange Notes</title>
    <link rel="stylesheet" href="profilestyle.css">
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, max-age=0">
    <meta http-equiv="Cache-Control" content="post-check=0, pre-check=0">
    <meta http-equiv="Pragma" content="no-cache">
    <script type="text/javascript">
        window.onload = function() {
            // Replace the current history state with a dummy URL
            history.replaceState(null, document.title, location.pathname + "#noback");

            // Push a new state so that the back button does not navigate back
            history.pushState(null, document.title, location.pathname);

            window.addEventListener('popstate', function(event) {
                // Redirect to login.jsp if the user tries to go back
                if (location.hash === "#noback") {
                    history.pushState(null, document.title, location.pathname);
                    window.location.href = "login.jsp";
                }
            });
        };
    </script>
</head>
<body>
    <form action="save">	
    <div class="parent">
        <div class="notevisual">
            <div class="title">
	   				<h1><textarea name="title"><%
		                    String title = (String) request.getAttribute("title");
		                    if (title == null) {
		                        title = ""; 
		                    }
		                    out.print(title);
                		%></textarea></h1>
            </div>
            <div class="notes">
                	<textarea name="notes"><%
		                    String notes = (String) request.getAttribute("notes");
		                    if (notes == null) {
		                        notes = ""; 
		                    }
		                    out.print(notes);
                		%></textarea>
                	<input type="hidden" name="username" value="<%=(String) request.getAttribute("username")%>">
	    			<input type="submit" value="Save">
            </div>
        </div>

        <div class="notelist">
		<% 
			List<List<String>> list = (List<List<String>>)request.getAttribute("list"); 
			if (list != null) {
			    for (List<String> li : list) {
			%>
			        <div class="option">
			            <a href="select?username=<%=(String) request.getAttribute("username")%>&title=<%=li.get(0)%>"><%=li.get(0)%></a>
			        </div>
			<%
			    }
			} else {
			%>
				<div class="option">
			            <a href="profile.jsp"><%="Title"%></a>
			        </div>
			<%   
			}
%>
        </div>
    </div>
  </form>
  <% 
    String errorMessage = (String)request.getAttribute("error"); 
    if(errorMessage != null) { 
%>
    <script>
        alert('<%= errorMessage %>');
    </script>
<% 
    } 
%>
</body>
</html>