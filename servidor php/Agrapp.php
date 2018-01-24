<?php  
$username = "";
$password = "";
if($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = $_POST['user'];
    $password = $_POST['pass'];
	
}else if($_SERVER['REQUEST_METHOD'] == 'GET') {
    $username = $_GET['user'];
    $password = $_GET['pass'];
	$abono= $_GET['abono'];
	$ph = $_GET['ph'];
    $temeperatura = $_GET['temperatura'];
	$humedad= $_GET['humedad'];
	$riego= $_GET['riego']
	
//$fecha = $_GET['codigo'];
	//$titulo = $_GET['titulo'];
	//$descripcion = $_GET['idimage'];
	//$precio = $_GET['precio'];
	
}
?>

<html>
<body>
<?php  
if($username!="" && $password!=""){
 
// Usuarios correctos:
// 		user1 pass=12345
// 		user2 pass=12345
// 		user3 pass=12345
    //$expires = mktime(date("H")+1, date("i"), date("s"), date("m")  , date("d"), date("Y"));
	if(($username=="user1" || $username=="user2" || $username=="user3") && $password=="12345"){
    	//echo 'SESION-ID=SID'.$username.base64_encode(sha1($username.$password.$expires)).'&EXPIRES='.date('Y-m-d-H-i-s',$expires);
		if($abono>101 && $abono<0){
		echo 'Abono incorrecto, introduzca unos valores entre 0 y 100';
		}
		elseif($temperatura>101 && $temperatura<0){
		echo 'La temperatura supera los valores posibles';
		}
		elseif($ph>15 && $ph<0){
			echo 'Ph incorrecto, introduzca de nuevo el abono';
		}
		elseif($humedad>101 && $humedad <0){
			echo 'Humedad incorrecta, introduzca de nuevo la humedad';
		}
		elseif($riego=!"ON"&& $riego=!"OFF" ){
			echo 'Valores posibles de riego solo ON y OFF, introduzca alguno de estos valores';
		}
		else{
			echo 'Ha introducido los valores correctos';
		}
			
	}else
		echo 'Contraseña o Usuario Incorrecto';
}else
    echo 'ERROR WRONG REQUEST';		 
?>

</body>
</html>