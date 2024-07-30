module thiagodnf.hangmanfx {

    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.media;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens thiagodnf.hangmanfx to javafx.fxml;
    opens thiagodnf.hangmanfx.controller;

    exports thiagodnf.hangmanfx;
    exports thiagodnf.hangmanfx.controller;
    exports thiagodnf.hangmanfx.model;
    exports thiagodnf.hangmanfx.util;

}
