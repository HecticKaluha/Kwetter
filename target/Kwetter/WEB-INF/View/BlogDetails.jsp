<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="x-iso-8859-11"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
        <script type="text/javascript" src="Script/AddComment.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=x-iso-8859-11">
        <title>Blog</title>
    </head>
    <body>               
        <div class="content-box middle-box">
            <div class="middle-head">
                <span class="placedBy">
                    by <c:out value="${kweet.getOwner()}"/> on <fmt:formatDate value="${kweet.getDate()}" pattern="d-M-yyyy HH:mm:ss" />
                </span>
            </div>
            <div class="middle-content">
                <c:out value="${kweet.getMessage()}"/>
            </div>
        </div>        
        <!--<div class="middle-head"><h2>Comments</h2></div>
        <div id="Comments">
            <c:forEach var="comment" items="${kweet.getComments()}">
                <div class="content-box middle-box">
                    <div class="middle-head">
                        By Anonymous on <fmt:formatDate value="${comment.getDate()}" pattern="d-M-yyyy HH:mm:ss" />
                    </div>
                    <div class="middle-content">
                        <c:out value="${comment.getContent()}"/>                    
                    </div>
                </div>
            </c:forEach>
        </div>-->
        <div class="middle-head"><h2>New comment</h2></div>
        <div class="middle-box">
            <textarea id="Comment" name="Comment"></textarea>
            <input id="AddComment" type="submit" value="Add Comment" onclick="AddComment(${kweet.getId()})"/>
        </div>
    </body>
</html>
