<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="x-iso-8859-11"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
        <script type="text/javascript" src="Script/ManagePost.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=x-iso-8859-11">
        <title>Blog Admin</title>
    </head>
    <body>
        <div class="content-box middle-box">
            <div class="middle-head">
                <h1>Admin</h1> 
            </div>
            <div class="middle-content">
                <label class="inputLabel" for="AuthorInput">Author:</label>
                <input id="AuthorInput" name="Author" type="text" />
                <label class="inputLabel" for="TitleInput">Title:</label>
                <input id="TitleInput" name="Title" type="text" />
                <label class="inputLabel" for="ContentInput">Content:</label></td>
                <textarea id="ContentInput" name="Content"></textarea>
                <input id="Save" type="submit" value="Add Post" onclick="AddPost()"/>
            </div>      
        </div>
        
        <div class="middle-content">
            <input id="ToggleAdvanced" type="submit" value="<c:out value="${advanced ? 'Basic...' : 'Advanced...'}"/>" onclick="ToggleAdvanced()"/>
        </div>
        <table id="Posts" <c:if test="${!advanced}">class="basic"</c:if>>
            <tr><th>Title</th><th>Date</th><th>Options</th></tr>
            <c:forEach var="post" items="${postings}">
                <tr id="Post${post.getId()}">
                    <td id="Title${post.getId()}"><c:out value="${post.getTitle()}"/></td>
                    <td width="160px" id="Date"><fmt:formatDate value="${post.getDate()}" pattern="d-M-yyyy HH:mm:ss" /></td>
                    <td width="160px">
                        <input type="submit" value="Edit" onclick="GetPost(${post.getId()})"/>
                        <input type="submit" value="Delete" onclick="DeletePost(${post.getId()})"/>
                    </td>
                </tr>
            </c:forEach>
        </table>  
        
    </body>
</html>
