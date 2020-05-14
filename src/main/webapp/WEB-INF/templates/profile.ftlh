<!doctype html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<style>
    .error {
        color: #ff0000;
    }
</style>
<body>
<div>
    <h1><@spring.message 'profile.page.welcome'/></h1>
    <h1><@spring.message 'profile.page.good.work'/></h1>
</div>
<div>
    <@spring.bind "profileForm"/>
    <form action="/profile" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        Email: <br>
        <@spring.formInput path="profileForm.email"/>
        <@spring.showErrors "<br>"/>
        <br><br>
        Username: <br>
        <@spring.formInput path="profileForm.username"/>
        <@spring.showErrors "<br>","error"/>
        <input type="submit" value="Submit">
    </form>
</div>
</body>
</html>
