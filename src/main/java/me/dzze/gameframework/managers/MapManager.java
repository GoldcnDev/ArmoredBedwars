package me.dzze.gameframework.managers;

import me.dzze.gameframework.Main;
import me.dzze.gameframework.utils.PathUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class MapManager {
    private final List<String> mapNames = new ArrayList<>();

    private final Path mapsFolder;

    private int currentMap = -1;

    public MapManager(Main main) {
        this.mapsFolder = main.getDataFolder().toPath().resolve("maps");
        if (Files.notExists(this.mapsFolder)) {
            try {
                Files.createDirectories(this.mapsFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (final DirectoryStream<Path> stream = Files.newDirectoryStream(this.mapsFolder)) {
            for (Path path : stream) {
                this.mapNames.add(path.getFileName().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAndUnloadCurrentMap() {
        Bukkit.unloadWorld(this.mapNames.get(this.currentMap), false);
        PathUtils.deleteDirectory(Paths.get(this.mapNames.get(this.currentMap)));
    }

    public World nextMap() {
        if (this.mapNames.isEmpty()) {
            return null;
        }

        if (this.currentMap > -1) {
            this.deleteAndUnloadCurrentMap();
        }

        if (this.currentMap == this.mapNames.size() - 1) {
            this.currentMap = 0;
        } else {
            this.currentMap++;
        }

        final String mapName = this.mapNames.get(this.currentMap);
        try {
            Files.copy(this.mapsFolder.resolve(mapName), Paths.get(""), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new WorldCreator(mapName).createWorld();
    }
}
