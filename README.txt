# codenation-juliocesar

1. fazer uma requisi��o HTTP pro endere�o que os caras apontaram -> recebemos um JSON
2. fazer o parse do JSON pra pegar o campo de offset, e a msg a ser cifrada
3. decifrar a msg, observando que:
  3-1. toda em letras min�sculas
  3-2. somente letras ser�o decifradas, caracteres de pontua��o n�o
4. calcular um sha1 do texto decifrado
5. escrever o texto decifrado e o sha1 nos respectivos campos do json
6. salvar o arquivo json como "answer"
7. fazer um POST HTTP para o url q eles deixaram, levando em conta que
  7-1. a msg POST tem que ter o formato espec�fico para o Content-Type: multipart/form-data;
  
  link da api: http://hc.apache.org/index.html
  