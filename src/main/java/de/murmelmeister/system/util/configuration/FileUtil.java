package de.murmelmeister.system.util.configuration;

import de.murmelmeister.system.Build;
import org.bukkit.configuration.file.YamlConfiguration;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Manage the files.
 */
public final class FileUtil {
    private static File file;

    /**
     * Create a new file.
     *
     * @param logger   Log the errors/warnings message in the console
     * @param fileName Name of the created file
     * @return A file that is created
     */
    public static File create(Logger logger, String fileName) {
        String name = fileName + ".yml";
        String path = "./plugins/" + Build.class.getSimpleName();
        File file = new File(path, name);
        File parent = file.getParentFile();
        if (!parent.exists()) {
            boolean b = parent.mkdirs();
            if (!b) logger.warn("The directory of the file can not be created.");
        }

        if (!file.exists()) {
            try {
                boolean b = file.createNewFile();
                if (!b) logger.error(String.format("The file '%s' can not be created.", name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    /**
     * Load and create the file.
     *
     * @param logger   Log the errors/warnings message in the console
     * @param fileName Name of the created file
     * @return A yaml config
     */
    public static YamlConfiguration load(Logger logger, String fileName) {
        FileUtil.file = FileUtil.create(logger, fileName);
        return YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Save the config.
     *
     * @param configuration Config that will be saved
     */
    public static void save(YamlConfiguration configuration) {
        try {
            configuration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
