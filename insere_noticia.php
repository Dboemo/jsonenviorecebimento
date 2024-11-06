<?php

$servidor = 'localhost';
$banco      = 'bdremoto';
$usuario  = 'root';
$senha    = '';

$conexao = mysqli_connect($servidor, $usuario, $senha, $banco);

$json = file_get_contents('php://input');
$obj = json_decode($json);

$texto1=$obj->autor;	
$texto2=$obj->titulo;
$texto3=$obj->conteudo;
$texto4=$obj->data;

$sql ="INSERT INTO noticia(autor, titulo, conteudo, data) VALUES ('".$texto1."','".$texto2."','".$texto3."','".$texto4."')";
mysqli_query($conexao,$sql);

?>

