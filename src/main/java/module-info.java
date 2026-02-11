module appli.dailystewardfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens appli.dailystewardfx to javafx.fxml;
    exports appli.dailystewardfx;
}