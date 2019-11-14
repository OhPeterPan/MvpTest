package com.wak.retrofit.task;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public interface ILoginTask extends ITask {
    void execute(Observer observer);
    void vExecute(Observer observer);
}
