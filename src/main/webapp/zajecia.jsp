<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Zajecia</title>
</head>
<body>
<form action="add" method="get">
    <label>Imię:<input type="text" id="name" name="name"/></label><br/>
    <label>Nazwisko:<input type="text" id="surname" name="surname"/></label><br/>
    <label>Pracodawca:<input type="text" id="employment" name="employment"/></label><br/>
    <label>Adres email:<input type="text" id="email" name="email"/></label><br/>
    <label>Potwierdź adres email:<input type="text" id="email" name="confirmemail"/></label><br/>
    <label>Skąd dowiedziałeś się o konferencji:</label><br/>
    <label>Praca<input type="radio" name="info" value="worl"/></label><br/>
    <label>Uczelnia<input type="radio" name="info" value="school"/></label><br/>
    <label>Facebook<input type="radio" name="info" value="facebook"/></label><br/>
    <label>Znajomi<input type="radio" name="info" value="friends"/></label><br/>
    <input type="submit" value="wyślij"/>
</form>
</body>
</html>