

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
	<title>BT</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<meta name="Language" content="es">
	<meta http-equiv="X-UA-Compatible" content="IE=8">
</head>

<body>

<?		
	$destinatario = "info@domain.com";		
	$asunto = $_GET["pmensaje"]; 
	$cuerpo  = " ";
	$cabeceras  = 'MIME-Version: 1.0' . "\r\n";
	$cabeceras .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
	$cabeceras .= 'To: info@domain.com ' . "\r\n";
	$cabeceras .= 'From: serverbot@mydomain.com ' . "\r\n";		
	$res=mail($destinatario,$asunto,$cuerpo,$cabeceras);
?>

	<script>
	window.close();
	</script>

</body>
</html>