<html>
<body>
<h2>Hello World!</h2>
<form action="${pageContext.request.contextPath}/test/signIn" method="post">
    <table>
        <tr>
            <td>User Name:</td>
            <td><input type="text" name="userName"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="sign in"/></td>
        </tr>
    </table>
</form>
</body>
</html>
