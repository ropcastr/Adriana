module fatec.gov.br {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens fatec.gov.br to javafx.fxml;
    exports fatec.gov.br;

    opens fatec.gov.br.e1.cadastrocsv to javafx.fxml;
    exports fatec.gov.br.e1.cadastrocsv;

    opens fatec.gov.br.e1.cadastrocsv.controller to javafx.fxml;
    exports fatec.gov.br.e1.cadastrocsv.controller;

    opens fatec.gov.br.e1.imagens.doodle to javafx.fxml;
    exports fatec.gov.br.e1.imagens.doodle;

    opens fatec.gov.br.e1.imagens.controllers to javafx.fxml;
    exports fatec.gov.br.e1.imagens.controllers;

    opens fatec.gov.br.e1.imagens.vikmuniz to javafx.fxml;
    exports fatec.gov.br.e1.imagens.vikmuniz;

    opens fatec.gov.br.e1.musica.controller to javafx.fxml;
    exports fatec.gov.br.e1.musica.controller;

    opens fatec.gov.br.e1.musica to javafx.fxml;
    exports fatec.gov.br.e1.musica;

    opens fatec.gov.br.e1.video.controller to javafx.fxml;
    exports fatec.gov.br.e1.video.controller;

    opens fatec.gov.br.e1.video to javafx.fxml;
    exports fatec.gov.br.e1.video;

}


