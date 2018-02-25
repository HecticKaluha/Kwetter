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
            <c:forEach var="posting" items="${postings}">
                <div class="content-box middle-box">
                    <div class="middle-head">
                        <h1><c:out value="${posting.getTitle()}"/></h1>
                        <span class="placedBy">
                            by <c:out value="${posting.getAuthor()}"/> on <fmt:formatDate value="${posting.getDate()}" pattern="d-M-yyyy HH:mm:ss" />
                        </span>
                    </div>
                    <div class="middle-content">
                        <c:out value="${posting.getContent()}"/>
                        <div class="comments">
                            <a href="<c:url value="/BlogDetails">
                                   <c:param name="id" value="${posting.getId()}"/>
                                </c:url>">Comments(<c:out value="${posting.getComments().size()}"/>)</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
