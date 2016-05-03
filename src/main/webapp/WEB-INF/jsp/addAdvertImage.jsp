<%--
  Created by IntelliJ IDEA.
  User: kate
  Date: 04.05.16
  Time: 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add advert Image</title>
    <jsp:include page="head-include.jsp"/>
    <script src="/js/addAdvertImage.js">
    </script>
</head>
<body>

<input id="advertId">
<span style="background-color:lemonchiffon" class="btn btn-default btn-file">
    <input class="form-control" type="file" name="file" id="advertImage"/>Upload image
</span>
<input type="button" class="btn btn-lg btn-success btn-block" value="add"
       onclick="addAdvertImage()">

</body>
</html>
