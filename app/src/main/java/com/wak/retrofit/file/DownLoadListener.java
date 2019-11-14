package com.wak.retrofit.file;

import java.io.File;

public interface DownLoadListener {
    void Success(File file);

    void progress(int progress);
}
