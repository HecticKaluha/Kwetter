<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="x-iso-8859-11"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=x-iso-8859-11">
        <title>Blog</title>
    </head>
    <body>
        <div>
            <c:forEach var="kweet" items="${postings}">
                <div class="content-box middle-box">
                    <div class="middle-head">
                        <span class="placedBy">
                            by <c:out value="${kweet.getOwner()}"/> on <fmt:formatDate value="${kweet.getDate()}" pattern="d-M-yyyy HH:mm:ss" />
                        </span>
                    </div>
                    <div class="middle-content">
                        <c:out value="${kweet.getMessage()}"/>
                        <div class="comments">
                            <a href="<c:url value="/BlogDetails">
                                   <c:param name="id" value="${kweet.getId()}"/>
                                </c:url>">Comments(<c:out value="${kweet.getComments().size()}"/>)</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
