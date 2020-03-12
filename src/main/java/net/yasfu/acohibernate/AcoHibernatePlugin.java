package net.yasfu.acohibernate;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

public class AcoHibernatePlugin extends JavaPlugin {

    private boolean sleeping = false;

    @Override
    public void onEnable() {
        String version = getDescription().getVersion();
        Logger logger = getLogger();

        Runnable checkSleep = new Runnable() {
            public void run() {
                if (Bukkit.getOnlinePlayers().isEmpty()) {
                    try {
                        if (!sleeping) {
                            Logger logger = getLogger();
                            logger.info("ZzZ..");
                            sleeping = true;
                        }

                        Thread.sleep(1000L);
                    } catch (Exception ignored) { }

                    return;
                }

                if (sleeping) {
                    Logger logger = getLogger();
                    logger.info("Waking up!");
                    sleeping = false;
                }
            }
        };

        logger.info("AcoHibernate Started - " + version);
        logger.info("Developed By LittleBigBug");
        logger.info("https://github.com/littlebigbug/acohibernate");

        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, checkSleep, 0L, 1L);
    }

    @Override
    public void onDisable() {
        String version = getDescription().getVersion();
        Logger logger = getLogger();

        logger.info("Stopping AcoHibernate - " + version);
    }

}
