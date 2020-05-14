<!doctype html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <form action="/login" method="post">
        <h1>Login</h1>
        <br>
        <input type="email" name="email" placeholder="Email">
        <br>
        <input type="password" name="password" placeholder="Password">
        <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <br>
        <label>
            <input type="checkbox" name="remember me">Запомни меня пж
        </label>
        <input type="submit" value="Хотю зайти">
    </form>
</div>
</body>
</html>