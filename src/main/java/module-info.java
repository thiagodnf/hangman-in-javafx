module tdnf.hangmanfx {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires static lombok;

    opens thiagodnf.hangmanfx to javafx.fxml;
    opens thiagodnf.hangmanfx.controller;

    exports thiagodnf.hangmanfx;
    exports thiagodnf.hangmanfx.controller;
    exports thiagodnf.hangmanfx.model;
    exports thiagodnf.hangmanfx.util;

}
