package threads;

import rx.Observable;
import rx.subjects.PublishSubject;

public class RxJavaSimple {
    public static void main(String[] args) {

        Observable<String> observable = Observable.create(observer -> {
            observer.onNext("One");
            observer.onNext("Tow");
        });
        observable.map(s -> String.join(", ", s)).toSingle().subscribe(System.out::println);

        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.subscribe(s -> System.out.println("s=" + s));
        publishSubject.subscribe(s -> System.out.println("d=" + s));
        publishSubject.onNext("Three");
        publishSubject.onNext("Four");
        publishSubject.onNext("Five");
        publishSubject.onCompleted();
    }
}
