<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Custom styles for this template -->

    <title>Analysis page</title>
    <link rel="stylesheet" href="css/slider.css"/>

</head>

<body>

<#if photoname??>
    <h1>Загруженное изображение</h1>
    <img src="/media?name=${photoname}" style="width: 400px; margin-bottom: 0rem">
    <br/>

    <details>
        <summary>Метаданные</summary>
        <p>
            <#list metadata as key, value>
        <table class="headerTable" style="table-layout: fixed; width:600px;">
            <tbody>
            <tr>
                <td style="width:100%;">${key}</td>
                <#if "${value}"?contains("Photoshop")>
                    <td bgcolor="pink" style="width:100%;">${value}</td>
                <#else>
                    <td style="width:100%;">${value}</td>
                </#if>
            </tr>
            </tbody>
        </table>
        </#list>
        </p>
    </details>

    <br/>
    <br/>
    <h2>Резкость</h2>
    <img src="/media?name=${sharped}" style="width: 400px; margin-bottom: 0rem">
    <form action="/sharp" method="post">
        <label for="alpha">Alpha</label>
        <br/>
        <p>Value: <span id="alpha-val"></span></p>
        <div id="myRangeContainer">
            <input type="range" id="alpha" name="alpha"
                   min="0.1" max="3" value="${alpha}" step="0.05" style="width: 400px" list="alpha_list">
            <datalist id="alpha_list">
                <option value="0" label="0"></option>
                <option value="0.25"></option>
                <option value="0.5"></option>
                <option value="0.75"></option>
                <option value="1" label="1"></option>
                <option value="1.25"></option>
                <option value="1.5"></option>
                <option value="1.75"></option>
                <option value="2" label="2"></option>
                <option value="2.25"></option>
                <option value="2.5"></option>
                <option value="2.75"></option>
                <option value="3" label="3"></option>
            </datalist>
        </div>
        <br/>
        <label for="beta">Beta</label>
        <br/>
        <p>Value: <span id="beta-val"></span></p>
        <div id="myRangeContainer">
            <input type="range" id="beta" name="beta"
                   min="-2" max="2" value="${beta}" step="0.05" style="width: 400px" list="beta_list">
            <datalist id="beta_list">
                <option value="-2" label="-2"></option>
                <option value="-1.75"></option>
                <option value="-1.5"></option>
                <option value="-1.25"></option>
                <option value="-1" label="-1"></option>
                <option value="-0.75"></option>
                <option value="-0.5"></option>
                <option value="-0.25"></option>
                <option value="0" label="0"></option>
                <option value="0.25"></option>
                <option value="0.5"></option>
                <option value="0.75"></option>
                <option value="1" label="1"></option>
                <option value="1.25"></option>
                <option value="1.5"></option>
                <option value="1.75"></option>
                <option value="2" label="2"></option>
            </datalist>
        </div>
        <br/>
        <input type="submit" value="OK"/>
    </form>


    <br/>
    <br/>
    <h2>Границы изображения</h2>
    <img src="/media?name=${edged}" style="width: 400px; margin-bottom: 0rem">
    <form action="/edge" method="post">
        <label for="edge">Edge</label>
        <br/>
        <p>Value: <span id="edge-val"></span></p>
        <div id="myRangeContainer">
            <input type="range" id="edge" name="edge"
                   min="0" max="100" value="${edge}" step="1" style="width: 400px" list="edge_list">
            <datalist id="edge_list">
                <option value="0" label="0"></option>
                <option value="25" label="25"></option>
                <option value="50" label="50"></option>
                <option value="75" label="75"></option>
                <option value="100" label="100"></option>
            </datalist>
        </div>
        <br/>

        <input type="submit" value="OK"/>
    </form>
</#if>


<script src="js/slider.js"></script>
</body>
</html>
