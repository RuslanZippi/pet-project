package main.service;

import lombok.SneakyThrows;
import main.base.currency.CurrencyBase;
import main.reposiroty.currency.CurrencyRep;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParseCurrency {

    @Autowired
    private CurrencyRep currencyRep;
    private static Document doc;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private void saveInBase(Document doc) {
        Elements names = doc.select("name");
        Elements dates = doc.select("ValCurs");
        Elements charCodes = doc.select("CharCode");
        Elements values = doc.select("value");

        CurrencyBase base;
        for (int index = 0; index < names.size(); index++) {
            base = new CurrencyBase();

            base.setDate(dates.get(0).attributes().get("Date"));
            base.setName(names.get(index).text());
            base.setCharCode(charCodes.get(index).text());
            base.setValue(values.get(index).text());

            currencyRep.save(base);
        }
    }


    @SneakyThrows
    public void setSite(String html) {
        doc = Jsoup.connect(html).get();
        String date = dateFormat.format(new Date());
        Iterable<CurrencyBase> iterable = currencyRep.findAll();
//        CurrencyBase base = iterable.iterator().next();
        if (!iterable.iterator().hasNext()) {
            saveInBase(doc);
        } else {
            CurrencyBase base = iterable.iterator().next();
            if (!base.getDate().equals(date)) {
                update(doc, iterable);
            }
        }
    }

    private void update(Document doc, Iterable<CurrencyBase> iterable) {
        Elements names = doc.select("name");
        Elements dates = doc.select("ValCurs");
        Elements charCodes = doc.select("CharCode");
        Elements values = doc.select("value");
        int index = 0;
        for (CurrencyBase base : iterable) {

            base.setDate(dates.get(0).attributes().get("Date"));
            base.setName(names.get(index).text());
            base.setCharCode(charCodes.get(index).text());
            base.setValue(values.get(index).text());

            currencyRep.save(base);
            index++;
        }
    }


}
