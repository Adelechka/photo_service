<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Custom styles for this template -->
    <title>Main page</title>
</head>

<body>

<h1> Загрузите фотографию для анализа! </h1>
<div>
    <form action="/upload" method="post" enctype="multipart/form-data">
        <input class="form-control" type="file" name="photo"
               accept="image/png, image/jpeg" placeholder="Выберите файл"/>
        <br/>
        <input type="submit" value="Проверить"/>
    </form>
</div>


</body>
</html>
