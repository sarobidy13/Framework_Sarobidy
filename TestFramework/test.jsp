
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Test JSP</title>
</head>
<body>
    <% Test t = (Test) request.getAttribute("obj");  %>
    id= <%= t.getId()  %><br>    
    nom= <%= t.getNom()  %>    
</body>
</html>