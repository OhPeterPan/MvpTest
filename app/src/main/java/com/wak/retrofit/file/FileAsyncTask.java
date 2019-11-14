package com.wak.retrofit.file;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class FileAsyncTask extends AsyncTask<ResponseBody, Integer, File> {
    public static final String DM_TARGET_FOLDER = File.separator + "dd" + File.separator; //下载目标文件夹
    private Context context;
    private DownLoadListener listener;

    public FileAsyncTask(Context context, DownLoadListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        listener.progress(values[0]);
    }

    @Override
    protected File doInBackground(ResponseBody... body) {
        ResponseBody resBody = body[0];
        File dirFile = new File(Environment.getExternalStorageDirectory() + DM_TARGET_FOLDER);
        createFolder(dirFile);
        File file = new File(dirFile, "a.apk");
        delFileOrFolder(file);

        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        byte[] buffer = new byte[8960];

        try {
            //file.createNewFile();
            inputStream = resBody.byteStream();
            outputStream = new FileOutputStream(file);
            int len;
            int total = 0;
            int progress;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
                total += len;
                progress = (int) (total * 100 / resBody.contentLength());
                publishProgress(progress);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(inputStream);
            closeQuietly(outputStream);
        }

        return file;
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        listener.Success(file);
    }

    public static boolean createFolder(File targetFolder) {
        if (targetFolder.exists()) {
            if (targetFolder.isDirectory()) return true;
            targetFolder.delete();
        }
        return targetFolder.mkdirs();
    }

    public static boolean delFileOrFolder(File file) {
        if (file == null || !file.exists()) {
            // do nothing
        } else if (file.isFile()) {
            file.delete();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File sonFile : files) {
                    delFileOrFolder(sonFile);
                }
            }
            file.delete();
        }
        return true;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
