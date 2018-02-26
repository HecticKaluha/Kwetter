<%@page contentType="text/html" pageEncoding="x-iso-8859-11"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="Css/Style.css" />
        <link href="http://fonts.googleapis.com/css?family=Ubuntu:regular,bold" rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=x-iso-8859-11">
        <title>My Blog</title>
    </head>
    <body>
        <div id="header">
            <div id="logo">My Blog</div>
            <div id="menu">
                <a href="<c:url value="/Blog"/>">Blog</a>
                |
                <a href="<c:url value="/Admin"/>">Admin</a>
            </div>
        </div>
        <div id="right-side">
            <div class="side-head">
                <h2>Archive</h2>
            </div>
            <div class="content-box side-content">
                <ul id="Archive" class="side-nav">
                    <c:forEach var="post" items="${postings}">
                        <li><a id="TitleSide${post.getId()}" href="<c:url value="/BlogDetails">
                                   <c:param name="id" value="${post.getId()}"/>
                               </c:url>"><c:out value="${post.getTitle()}"/></a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div id="middle">
            <c:import url="${content}" />
        </div>
        <div id="footer">
        </div>
    </body>
</html>
