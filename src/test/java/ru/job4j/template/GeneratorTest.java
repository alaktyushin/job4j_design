package ru.job4j.template;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceWhenValidTemplateAndMapIsEmpty() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        assertThat(generator.produce(template, map), is ("I am a Petr Arsentev, Who are you?"));
    }

    @Ignore
    @Test
    public void produceWhenValidTemplateAndValidMap() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        assertThat(generator.produce(template, map), is ("I am a Petr Arsentev, Who are you?"));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceWhenInvalidTemplate() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name} ${family_name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        assertThat(generator.produce(template, map), is ("I am a Petr Arsentev, Who are you?"));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceWhenValidTemplateAndInvalidMap() {
        Generator generator = new TemplateGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subj", "you");
        assertThat(generator.produce(template, map), is ("I am a Petr Arsentev, Who are you?"));
    }
}