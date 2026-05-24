# EstantX Virtual
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)
Aplicação de desktop para organização de livros, você pode cadastrar, remover, atualizar e buscar seus livros.

## Tecnologias
- Java 17
- JavaFX 21.0.11
- CSS

## Instalação e execução

Com o Java (verifique se é a versão 17+) já instalado em sua máquina, você deve instalar o SDK do JavaFX (atente-se ao detalhe de fazer o download da versão 21.0.1) em https://openjfx.io/. Após isso, devemos identificar o caminho da nossa instalação. Copie-o, abra o terminal dentro da pasta do projeto e digite:
Linux/macOS
```bash
#Verifique a versão do Java
java --version
#Compila o projeto
javac --module-path <caminho_javafx>/lib --add-modules javafx.controls,javafx.graphics -d bin $(find src -name "*.java")
#Roda a aplicação
java --module-path <caminho_javafx>/lib --add-modules javafx.controls,javafx.graphics -cp bin application.App
```
Windows
```powershell
#Compila o projeto
javac --module-path "<caminho_javafx>\lib" --add-modules javafx.controls,javafx.graphics -d bin (Get-ChildItem -Path src -Filter *.java -Recurse | ForEach-Object { $_.FullName })

# Roda a aplicação
java --module-path "<caminho_javafx>\lib" --add-modules javafx.controls,javafx.graphics -cp bin application.App
```
## Contribuição
Contribuições são sempre bem-vindas! Se você encontrou um bug ou tem sugestões de melhorias:
1. Abra uma Issue relatando o problema.
2. Faça um Fork do projeto, crie uma Branch para sua modificação e envie um Pull Request.

Faça me um pix.
