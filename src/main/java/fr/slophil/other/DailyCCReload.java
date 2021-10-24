package fr.slophil.other;

import fr.slophil.FuturaPlugin;

import java.time.LocalTime;

public class DailyCCReload {

    private FuturaPlugin main;
    public DailyCCReload(FuturaPlugin futuraPlugin) {
        this.main = futuraPlugin;
    }

    LocalTime configTime = LocalTime.parse(main.getConfig().getString("Time"));
    LocalTime nowTime = LocalTime.now();

    if (nowTime.compareTo(configTime)){

    }

}
