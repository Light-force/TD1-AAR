<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Pendu</title>
</head>
<body>
<p>bienvenu dans le jeu de pendu...</p>
Quel mot proposez-vous ?
<form method="post" action="init">
    <input type="text" name="lemot">
    <button type="submit">Envoyer</button>
</form>
</body>
</html>