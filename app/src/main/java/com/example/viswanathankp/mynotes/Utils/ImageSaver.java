package com.example.viswanathankp.mynotes.Utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by viswanathan.kp on 27/02/18.
 */

public class ImageSaver {

    private static String directoryName = "images";
    private static String fileName = "image_";
    private static String extension = ".jpg";

    public ImageSaver() {
    }

    private static String getFileName(){
        return fileName + System.currentTimeMillis() + extension;
    }

    public static String save(Context context, Bitmap bitmapImage) {
        FileOutputStream stream = null;
        ContextWrapper wrapper = new ContextWrapper(context.getApplicationContext());
        File file = wrapper.getDir(directoryName, MODE_PRIVATE);
        file = new File(file, getFileName());
        try {
            stream = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
