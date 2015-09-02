package com.yandex.money.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.yandex.money.api.model.showcase.Fee;
import com.yandex.money.api.model.showcase.components.container.Group;
import com.yandex.money.api.model.showcase.components.container.Paragraph;
import com.yandex.money.api.model.showcase.components.uicontrol.Amount;
import com.yandex.money.api.model.showcase.components.uicontrol.Checkbox;
import com.yandex.money.api.model.showcase.components.uicontrol.Date;
import com.yandex.money.api.model.showcase.components.uicontrol.Email;
import com.yandex.money.api.model.showcase.components.uicontrol.Month;
import com.yandex.money.api.model.showcase.components.uicontrol.Number;
import com.yandex.money.api.model.showcase.components.uicontrol.Select;
import com.yandex.money.api.model.showcase.components.uicontrol.Submit;
import com.yandex.money.api.model.showcase.components.uicontrol.Tel;
import com.yandex.money.api.model.showcase.components.uicontrol.Text;
import com.yandex.money.api.model.showcase.components.uicontrol.TextArea;
import com.yandex.money.api.typeadapters.FeeTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.container.GroupTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.container.ParagraphTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.AmountTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.CheckboxTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.DateTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.EmailTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.MonthTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.NumberTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.SelectTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.SubmitTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.TelTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.TextAreaTypeAdapter;
import com.yandex.money.api.typeadapters.showcase.uicontrol.TextTypeAdapter;

import org.junit.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.Scanner;

/**
 * Checks {@link com.yandex.money.api.model.showcase.components.Component}'s {@link Gson} type
 * adapters.
 *
 * @author Anton Ermak (ermak@yamoney.ru)
 */
public final class ComponentTypeAdapterTest {

    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Amount.class, AmountTypeAdapter.INSTANCE)
            .registerTypeAdapter(Number.class, NumberTypeAdapter.INSTANCE)
            .registerTypeAdapter(Month.class, MonthTypeAdapter.INSTANCE)
            .registerTypeAdapter(Date.class, DateTypeAdapter.INSTANCE)
            .registerTypeAdapter(Email.class, EmailTypeAdapter.INSTANCE)
            .registerTypeAdapter(TextArea.class, TextAreaTypeAdapter.INSTANCE)
            .registerTypeAdapter(Text.class, TextTypeAdapter.INSTANCE)
            .registerTypeAdapter(Checkbox.class, CheckboxTypeAdapter.INSTANCE)
            .registerTypeAdapter(Fee.class, FeeTypeAdapter.getInstance())
            .registerTypeAdapter(Tel.class, TelTypeAdapter.INSTANCE)
            .registerTypeAdapter(Select.class, SelectTypeAdapter.INSTANCE)
            .registerTypeAdapter(Submit.class, SubmitTypeAdapter.INSTANCE)
            .registerTypeAdapter(Group.class, GroupTypeAdapter.INSTANCE)
            .registerTypeAdapter(Paragraph.class, ParagraphTypeAdapter.INSTANCE)
            .create();

    @Test
    public void testCheckbox() {
        check("checkbox.json", Checkbox.class);
    }

    @Test
    public void testText() {
        check("text.json", Text.class);
    }

    @Test
    public void testTextArea() {
        check("textarea.json", TextArea.class);
    }

    @Test
    public void testEmail() {
        check("email.json", Email.class);
    }

    @Test
    public void testDate() {
        check("date.json", Date.class);
    }

    @Test
    public void testMonth() {
        check("month.json", Month.class);
    }

    @Test
    public void testSelectNoGroup() {
        check("select_nogroup.json", Select.class);
    }

    @Test
    public void testNumber() {
        check("number.json", Number.class);
    }

    @Test
    public void testSelect() {
        check("select_group.json", Select.class);
    }

    @Test
    public void testTel() {
        check("tel.json", Tel.class);
    }

    @Test
    public void testSubmit() {
        check("submit.json", Submit.class);
    }

    @Test
    public void testParagraph() {
        check("paragraph.json", Paragraph.class);
    }

    @Test
    public void testAmountStdFee() {
        check("amount_stdfee.json", Amount.class);
    }

    @Test
    public void testAmountCustomFee() {
        check("amount_customfee.json", Amount.class);
    }

    private static String loadComponentJson(String name) {
        return new Scanner(ComponentTypeAdapterTest.class
                .getResourceAsStream("/components/" + name), "UTF-8").useDelimiter("\\A").next();
    }

    /**
     * Reads JSON file and asserts it for equality after deserialization and serialization steps.
     *
     * @param jsonFileName JSON file name in resources.
     * @param type         type of expected {@link com.yandex.money.api.model.showcase.components
     * .Component}.
     */
    private static void check(String jsonFileName, Type type) {
        String json = loadComponentJson(jsonFileName);

        Object deserializedComponent = GSON.fromJson(json, type);
        Assert.assertEquals(new JsonParser().parse(json), GSON.toJsonTree(deserializedComponent));
    }
}
