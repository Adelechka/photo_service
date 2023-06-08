<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Custom styles for this template -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main page
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>


<body class="d-flex h-100 center-block text-center text-black bg-dark-subtle">
<div class="container" style="margin-top: 20% ">
    <div class=" d-flex w-100 h-100 p-3 m-auto flex-column">
        <main class="px-3 m-auto">
            <h1>Загрузите фотографию для анализа!</h1>

            <form action="/upload" method="post" enctype="multipart/form-data">

                <div class="input-group">
                    <input type="file" class="form-control" id="inputGroupFile04" name="photo"
                           accept="image/png, image/jpeg" aria-describedby="inputGroupFileAddon04"
                           placeholder="Выберите файл">
                    <button class="btn btn btn-outline-dark" type="submit" id="inputGroupFileAddon04">Проверить
                    </button>

                </div>
            </form>
        </main>

    </div>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>

</html>
