<?php
$servidor = 'localhost';
$banco      = 'bdremoto';
$usuario  = 'root';
$senha    = '';

$conexao = mysqli_connect($servidor, $usuario, $senha, $banco);
$dados = mysqli_query($conexao, "SELECT * FROM noticia");
while ($func = mysqli_fetch_assoc($dados)):
 $vetor['noticia'][]=array_map('utf8_encode',$func);
 endwhile;
 echo json_encode($vetor);
 
?>

