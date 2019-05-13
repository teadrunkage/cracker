package ru.ncedu.schek.cracker.bot;

import java.util.*;
import java.util.regex.*;

public class SimpleBot {
    final String[] COMMON_PHRASES = {
        "Нет ничего ценнее слов, сказанных к месту и ко времени.",
        "Порой молчание может сказать больше, нежели уйма слов.",
        "Перед тем как писать/говорить всегда лучше подумать. Извините, вырвалось...",
        "Вежливая и грамотная речь говорит о величии души.",
        "Приятно когда текст без орфографических ошибок.",
        "Многословие есть признак неупорядоченного ума. Прошу прощения...",
        "Слова могут ранить, но могут и исцелять.",
        "Записывая слова, мы удваиваем их силу.",
        "Кто ясно мыслит, тот ясно излагает.",
        "Боюсь Вы что-то не договариваете."};
    final String[] ELUSIVE_ANSWERS = {
        "Вопрос непростой, прошу тайм-аут на раздумья.",
        "Не уверен, что располагаю такой информацией.",
        "Поверьте, я сам хотел бы это знать, но увы...",
        "Вы действительно хотите это знать?",
        "Вы уверены, что Ваш вопрос сформулирован правильно?",
        "Боюсь, я не понял сути вопроса(.",
        "Давайте сохраним интригу? Шутка. К сожалению, я не могу ответить"};
    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{
        // hello
        put("хай", "hello");
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        // who
        put("кто\\s.*ты", "name");
        put("ты\\s.*кто", "name");
        put("как\\s.*зовут", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        // yes
        put("^да", "yes");
        put("согласен", "yes");
        // whattime
        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        // bye
        put("прощай", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");
        // model
        put("модель", "model");
        put("телефон", "model");
        // shop
        put("магазин", "model");
        put("купить", "model");
        put("покупка", "model");
        // info
        put("info", "info");
        put("справка", "info");
        put("справк*", "info");
        // find
        put("найди", "find"); 
        
    }};
    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{
        put("hello", "Добрый день, мы рады видеть Вас в нашем магазине. Если Вам нужна справка по магазину, наберите info.");
        put("name", "Я бот маркетплейса \"Cracker shop\". Для получения справки, наберите info.");
        put("yes", "Извините, повторите, пожалуйста, какой был вопрос.");
        put("bye", "До свидания. Удачных покупок.");
        put("model", "Ищете модель? Воспользуйтесь нашим поиском или скажите мне название модели с ключевым словом \"найди\"");
        put("shop", "Если вы ищете, где заказать конкретный телефон, нажмите на ссылку \"Go to shop\" в списке доступных моделей. Если же нет, вот списока наших магазинов: ... Уверен, Вы найдете то, что ищете.");
        put("info", "Справка по магазину: /n"
        		+ "1) Посмотреть модели можно во вкладке \"Магазин\".\n"
        		+ "2) Чтобы познакомиться с моделью поближе, нажмите на ее изображение.\n"
        		+ "3) Для покупки модели перейдите в нужный магазин по ссылке \"Go to shop\".\n");
    }};
    Pattern pattern; // for regexp
    Random random; // for random answers
    Date date; // for date and time

    public SimpleBot() {
        random = new Random();
        date = new Date();
    }

    public String sayInReturn(String msg, boolean ai) {
        String say = (msg.trim().endsWith("?"))?
            ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
            COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
        if (ai) {
            String message =
                String.join(" ", msg.toLowerCase().split("[!$%#@^&*() {,|.}?]+"));
            for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
                pattern = Pattern.compile(o.getKey());
                if (pattern.matcher(message).find())
                    if (o.getValue().equals("whattime")) return date.toString();
                    else if (o.getValue().equals("find")) {
                    	return getSearchFromString(message);
                    }
                    else return ANSWERS_BY_PATTERNS.get(o.getValue());
            }
        }
        return say;
    }
    
    public String getSearchFromString(String str) {
    	str = str.replace("найди ","");
    	String search = "http://localhost:5030/search?text="+str;
    	return search;
    }
    
    public String getLinkFromString(String str) {
    	String ret = "<a href=\""+str+"\"></a>";
    	return ret;
    }
}
