package ru.netology.ak1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress gameProgress = new GameProgress(94, 2, 2, 100);
        GameProgress gameProgress2 = new GameProgress(80, 4, 3, 200);
        GameProgress gameProgress3 = new GameProgress(70, 6, 4, 300);

        String[] savedGames = {"1.dat", "2.dat", "3.dat"};

        for (int i = 0; i < savedGames.length; i++) {

            try (FileOutputStream fos = new FileOutputStream("C:/Games/savegames/" + savedGames[i]);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(gameProgress);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        String zipArchive = "C:/Games/savegames/archive.zip";
        String[] savedProgress = {"C:/Games/savegames/1.dat", "C:/Games/savegames/2.dat", "C:/Games/savegames/3.dat"};

        try {
            FileOutputStream fos = new FileOutputStream(zipArchive);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < savedProgress.length; i++) {

                File searchFile = new File(savedProgress[i]);

                ZipEntry zipEntry = new ZipEntry(searchFile.getName());
                zos.putNextEntry(zipEntry);
                zos.write(Files.readAllBytes(searchFile.toPath()));
                zos.closeEntry();
            }
            zos.close();

        } catch (IOException ioe) {
            System.out.println("Ошибка создания массива: " + ioe);
        }

        String[] savedGamesForDelete = {"1.dat", "2.dat", "3.dat"};

        for (int i = 0; i < savedGamesForDelete.length; i++) {
            File file = new File("C:/Games/savegames/" + savedGamesForDelete[i]);
            if (file.delete()) {
                System.out.println("Файл " + savedGamesForDelete[i] + " успешно удален");
            }
        }

    }
}

