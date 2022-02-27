<html>
<head>
    <link rel="stylesheet" href="/resources/style.css">
</head>

<body>
<h1 class="center2">Welcome to Air Fare</h1>

<h2 style="text-align:center">Search for flights</h2>
<form action="http://localhost:9999/deals" method="post">
    <div class="column">
            <label for="source">Departure airport : </label>
            <input type="text" id="source" required/>
        </div>
    <div class="column">
        <label for="price">Discounted Fare : </label>
        <input type="text" id="price" required/>
    </div>
    <div class="column">
        <label for="deal_end">Deal ends : </label>
        <input type="time" id="deal_end" required/>
    </div>
    <input class="button" type="submit" value="Add deal">
</form>

</body>
</html>
