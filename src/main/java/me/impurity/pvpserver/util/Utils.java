package me.impurity.pvpserver.util;

import me.impurity.pvpserver.Instance;
import org.bukkit.*;

import java.text.DecimalFormat;
import java.util.concurrent.ThreadLocalRandom;

public class Utils implements Instance {
    private static final DecimalFormat format = new DecimalFormat("#.##");

    public static String formatLocation(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        World world = location.getWorld();
        return "&r&a" + world.getName()
                + " &r&3X:&r&a " + format.format(x)
                + " &r&3Y:&r&a " + format.format(y)
                + " &r&3Z:&r&a " + format.format(z);
    }

    public static ChatColor getTPSColor(String input) {
        if (input.equals("*20")) return ChatColor.GREEN;
        double i = Double.parseDouble(input);
        if (i >= 18.0D) return ChatColor.GREEN;
        return i >= 13.0D && i < 18.0D ? ChatColor.YELLOW : ChatColor.RED;
    }

    public static String getTPSAsString() {
        String tps = format.format(Bukkit.getTPS()[0]);
        return getTPSColor(tps) + tps;
    }

    public static double getRawTps() {
        return Bukkit.getTPS()[0];
    }

    public static Location hubLocation() {
        World world = Bukkit.getWorld("world");
        Location location = new Location(world, config.getDouble("SpawnPoint.x"), config.getDouble("SpawnPoint.y"), config.getDouble("SpawnPoint.z"));
        location.setYaw(config.getInt("SpawnPoint.yaw"));
        location.setPitch(config.getInt("SpawnPoint.pitch"));
        return location;
    }

    public static Location kitCreatorLocation() {
        World world = Bukkit.getWorld("world_the_end");
        Location location = new Location(world, config.getDouble("KitCreator.x"), config.getDouble("KitCreator.y"), config.getDouble("KitCreator.z"));
        location.setYaw(config.getInt("KitCreator.yaw"));
        location.setPitch(config.getInt("KitCreator.pitch"));
        return location;
    }

    private static int randomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static Location randomNetherLocation() {
        World world = Bukkit.getWorld("world_nether");
        int x = randomNumber(-config.getInt("Nether.x"), config.getInt("Nether.x"));
        int z = randomNumber(-config.getInt("Nether.z"), config.getInt("Nether.z"));
        int y = 0;
        Location location = new Location(world, x, y, z);
        Chunk chunk = location.getChunk();
        if (!chunk.isLoaded()) {
            chunk.load(true);
            assert world != null;
            location.setY(world.getHighestBlockYAt(location));
        }
        return location;
    }
}
