<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Essai</title>
</head>
<body>
<p>Il est temps de deviner...</p>
Quel caractère proposez-vous ?
<form method="post" action="play">
    <input type="text" name="lecaractere" maxlength="1">
    <button type="submit">Envoyer</button>
</form>

<p>Nombre d'essais restants : <c:out value="${nbEssaisRestants}" /></p>
Pour l'instant vous avez trouvé : <c:out value="${devine}" />

</body>
</html>
