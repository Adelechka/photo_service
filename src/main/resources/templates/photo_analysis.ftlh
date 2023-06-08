<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Custom styles for this template -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Analysis page
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link rel="stylesheet" href="css/slider.css"/>
</head>

<script>
    const myModal = document.getElementById('myModal')
    const myInput = document.getElementById('myModal')

    myModal.addEventListener('shown.bs.modal', () => {
        myInput.focus()
    })
</script>

<body class="d-flex h-100 center-block text-center text-black bg-dark-subtle">
<div class="container text-center">
    <div class="row align-items-center">

        <#if photoname??>
            <div class="row">
                <div class="col align-self-center">
                    <h1>Загруженное изображение</h1>
                    <img src="/media?name=${photoname}" class="rounded mx-auto d-block"
                         style="width: 400px; margin-bottom: 0rem">
                    <br/>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <details>
                        <summary>Метаданные</summary>
                        <table class="table table-sm headerTable">
                            <tbody>
                            <#list metadata as key, value>
                                <tr>
                                    <th scope="row">${key}</th>
                                    <#if "${value}"?contains("Photoshop")>
                                        <td class="table-danger">${value}</td>
                                    <#else>
                                        <td>${value}</td>
                                    </#if>
                                </tr>
                            </#list>
                            </tbody>
                        </table>
                    </details>
                </div>
            </div>

            <div class="container text-center">
                <div class="row  align-items-center">
                    <div class="col-md-4">
                        <h2>Резкость</h2>
                        <img class="rounded mx-auto d-block" src="/media?name=${sharped}" style="max-width: 400px">
                        <form action="/sharp" method="post">
                            <p class="h5">Alpa value: <span id="alpha-val"></span></p>
                            <div id="myRangeContainer">
                                <input type="range" id="alpha" name="alpha"
                                       min="0.1" max="3" value="${alpha}" step="0.05" style="width: 400px"
                                       list="alpha_list" class="form-range">
                            </div>
                            <p class="h5">Beta value: <span id="beta-val"></span></p>
                            <div id="myRangeContainer">
                                <input type="range" id="beta" name="beta"
                                       min="-2" max="2" value="${beta}" step="0.05" style="width: 400px"
                                       list="beta_list" class="form-range">
                            </div>
                            <input class="btn btn-primary" type="submit" value="применить"/>
                        </form>
                    </div>

                    <div class="col-md-4">
                        <h2>Границы изображения</h2>
                        <img class="rounded mx-auto d-block" src="/media?name=${edged}" style="max-width: 400px">
                        <form action="/edge" method="post">
                            <p class="h5">Edge value: <span id="edge-val"></span></p>
                            <div id="myRangeContainer">
                                <input type="range" id="edge" name="edge"
                                       min="0" max="100" value="${edge}" step="1" style="width: 400px" list="edge_list"
                                       class="form-range">
                            </div>
                            <input class="btn btn-primary" type="submit" value="применить"/>
                        </form>
                    </div>

                    <div class="col-md-4">
                        <h2>Анализ шумов</h2>
                        <img class="rounded mx-auto d-block" src="/media?name=${threshed}" style="max-width: 400px">
                        <form action="/noise" method="post">
                            <p class="h5">Noise value: <span id="thresh-val"></span></p>
                            <div id="myRangeContainer">
                                <input type="range" id="thresh" name="thresh"
                                       min="1" max="5" value="${thresh}" step="0.5" style="width: 400px"
                                       list="thresh_list" class="form-range">
                            </div>
                            <input class="btn btn-primary" type="submit" value="применить"/>
                        </form>
                    </div>
                </div>
            </div>
        </#if>
    </div>
</div>
<script src="js/slider.js"></script>
<script src="/docs/5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

</body>
</html>
