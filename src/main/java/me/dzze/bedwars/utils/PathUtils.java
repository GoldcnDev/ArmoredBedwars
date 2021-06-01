package me.dzze.bedwars.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathUtils {
    public static void deleteDirectory(Path path) {
        try (Stream<Path> walk = Files.walk(path)) {
            for (Path p : walk.collect(Collectors.toList())) {
                try {
                    Files.delete(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*walk.sorted(Comparator.reverseOrder()).forEach(p -> {
            });*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
