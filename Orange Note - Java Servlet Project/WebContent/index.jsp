<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>&#x1F34A; Orange Notes</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="bigcontainer">
        <h1>Begin Your Orange Notes Experience</h1>
        <div class="container">
            <h2>Register</h2>
            <form id="signup-form" action="newUser" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit">Sign Up</button>
            </form>
            <p>or</p>
            <p>Already have an account?</p>
            <a href="login.jsp" class="login-link">Login</a>
        </div>
    </div>
</body>
</html>
