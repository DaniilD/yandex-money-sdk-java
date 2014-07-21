package com.yandex.money.model.cps.misc;

/**
 * @author Slava Yasevich (vyasevich@yamoney.ru)
 */
public abstract class MoneySource {

    private final String id;

    protected MoneySource(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}