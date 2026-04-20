<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mysqli</title>
    <style>
        td,table{
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <?php
    $host = "localhost";
    $user = "root";
    $pass = "";
    $db = "mieszkania";
    
    // MYSQLI
    $conn = mysqli_connect($host, $user, $pass, $db);
    if ($conn->connect_error) {
        die("Błąd połączenia: " . $conn->connect_error);
    }
    
    $query = "SELECT id_mieszkania, ulica, nr_klatki, nr_mieszkania, metraz FROM adres WHERE metraz > 100 AND ulica LIKE 'K%' ORDER BY metraz DESC";
    $result = $conn->query($query);
    if ($result->num_rows > 0) {
        echo "MYSQLI: <br>";
        echo "<table>";   
        while ($row = $result->fetch_assoc()) {
        echo "<tr>"."<td>". $row["id_mieszkania"] ."</td>"."<td>". $row["ulica"]. "</td>"."<td>"."Numer klatki: ". $row["nr_klatki"]. "</td>"."<td>"."Numer mieszkania: ". $row["nr_mieszkania"]. "</td>"."<td>"."Metraż: ". $row["metraz"]. "</td>"."</tr>";
        }
        echo "</table>";
    }else {
        echo "Nie ma mieszkań takich.";
    }

    //PDO
    $dsn = "mysql:host=localhost;dbname=mieszkania;charset=utf8";

    try {
        $pdo = new PDO($dsn, $user, $pass);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        $stmt = $pdo->query("SELECT id_mieszkania, ulica, nr_klatki, nr_mieszkania, metraz FROM adres WHERE metraz > 100 AND ulica LIKE 'K%' ORDER BY metraz DESC");
        echo "PDO: <br>";
        echo "<table>";
        while ($row = $stmt->fetch(PDO::FETCH_ASSOC)) {
            echo "<tr>"."<td>". $row["id_mieszkania"] ."</td>"."<td>". $row["ulica"]. "</td>"."<td>"."Numer klatki: ". $row["nr_klatki"]. "</td>"."<td>"."Numer mieszkania: ". $row["nr_mieszkania"]. "</td>"."<td>"."Metraż: ". $row["metraz"]. "</td>"."</tr>";
        }
        echo "</table>";
    } catch (PDOException $e) {
        echo "Błąd bazy danych: " . $e->getMessage();
    }
    ?>
</body>
</html>