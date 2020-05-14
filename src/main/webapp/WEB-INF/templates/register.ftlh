<!doctype html>
<#import "spring.ftl" as spring />
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<style>
    .error {
        color: #ce2222;
    }
</style>
<body>
<div align="center">
    <form action="/register" method="post">
        <h1>Registration</h1>

        Email: <br>
        <@spring.formInput path="registerForm.email"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        Password: <br>
        <@spring.formInput path="registerForm.password"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        Username: <br>
        <@spring.formInput path="registerForm.username"/>
        <@spring.showErrors "<br>","error"/>
        <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <br>
        <input type="submit" value="Хотю зарегаться">
    </form>
</div>
</body>
</html>