module tdnf.hangmanfx {
	
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.media;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    
    requires static lombok;
    
    opens tdnf.hangmanfx to javafx.fxml;
    opens tdnf.hangmanfx.controller;
    
    exports tdnf.hangmanfx;
    exports tdnf.hangmanfx.controller;
    exports tdnf.hangmanfx.model;
    exports tdnf.hangmanfx.util;
    
}