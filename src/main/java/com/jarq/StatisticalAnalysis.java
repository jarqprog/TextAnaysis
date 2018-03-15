package com.jarq;

import com.jarq.iterators.CharIterator;

import java.util.*;

public class StatisticalAnalysis {

    private Iterator<String> iterator;
    private Map<String,Integer> dictionary;
    private List<String> dataCollection;

    public StatisticalAnalysis(Iterator<String> it) {
        iterator = it;
        dictionary = new HashMap<>();
        dataCollection = new ArrayList<>();
        setData();
    }

    public Integer countOf(String... elements) {
        return null;
    }

    public Integer dictionarySize() {
        return dictionary.size();
    }

    public Integer size() {
        return dataCollection.size();
    }

    public Set<String> occurMoreThan(Integer num) {
        return new HashSet<>();
    }

    private void setData() {
        while(iterator.hasNext()) {
            String element = iterator.next();
            dataCollection.add(element);
            if(dictionary.containsKey(element)){
                dictionary.put(element, dictionary.get(element)+1);
            } else {
                dictionary.put(element, 1);
            }
        }
    }

    public Map<String, Integer> getDictionary() {
        return dictionary;
    }

    public List<String> getDataCollection() {
        return dataCollection;
    }

    public Boolean hasCharIterator() {
        return iterator instanceof CharIterator;
    }
}
