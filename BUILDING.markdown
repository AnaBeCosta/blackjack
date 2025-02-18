# Unix, Linux or Mac OS X / Unix, Linux ou Mac OS X

## English

To compile on a Unix system, use:

    % tools/build
    
This will generate class files in the `src/` directory which you can run using:

    % cd src/
    % java main.java.main.java.balckjack.Blackjack

Also, a `.jar` file will be placed in the `bin/` directory that can be run independent of the location of the other source files. On Mac OS X, you can double-click this file to run it. However, on some Unix systems, you can't and therefore will need to run it using the following:

    % cd bin/
    % java -jar main.java.main.java.balckjack.Blackjack.jar

Additionally, I've provided a 'clean' script for Unix that will remove all generated `*.class` and `.jar` files.

    % tools/clean

## Windows XP

On a Windows system, to build:

    % cd src
    % javac main.java.main.java.balckjack.Blackjack.java
    
To run the game:

    % java main.java.main.java.balckjack.Blackjack


---

# Windows XP / Windows XP

## Português

Para compilar em um sistema Unix, use:

    % tools/build

Isso irá gerar os arquivos `.class` no diretório `src/`, que você pode executar com:

    % cd src/
    % java main.java.main.java.balckjack.Blackjack

Além disso, um arquivo `.jar` será colocado no diretório `bin/`, que pode ser executado independentemente da localização dos outros arquivos fonte. No Mac OS X, você pode clicar duas vezes nesse arquivo para executá-lo. Porém, em alguns sistemas Unix, isso não é possível, e será necessário executá-lo usando o seguinte comando:

    % cd bin/
    % java -jar main.java.main.java.balckjack.Blackjack.jar

Adicionalmente, foi fornecido um script "clean" para Unix que irá remover todos os arquivos gerados (*.class e *.jar).

    % tools/clean

## Windows XP

Em um sistema Windows, para compilar:

    % cd src
    % javac main.java.main.java.balckjack.Blackjack.java

Para executar o jogo:

    % java main.java.main.java.balckjack.Blackjack
